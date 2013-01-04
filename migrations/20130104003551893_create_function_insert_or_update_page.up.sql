-- Copyright (c) 2013 Dylon Edwards
--
-- Permission is hereby granted, free of charge, to any person obtaining a copy
-- of this software and associated documentation files (the "Software"), to deal
-- in the Software without restriction, including without limitation the rights
-- to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
-- copies of the Software, and to permit persons to whom the Software is
-- furnished to do so, subject to the following conditions:
--
-- The above copyright notice and this permission notice shall be included in
-- all copies or substantial portions of the Software.
--
-- THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
-- IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
-- FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
-- AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
-- LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
-- OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
-- SOFTWARE.

CREATE OR REPLACE FUNCTION insert_or_update_page(id integer
                                               , revision integer
                                               , title varchar(255)
                                               , redirect varchar(255)
                                               , text text)
RETURNS pages AS $body$
  DECLARE
    page pages%rowtype;
  BEGIN
    -- If the page corresponding to this one's redirect parameter does not
    -- exist, then create it.
    IF $4 IS NOT null THEN
      PERFORM true
      FROM pages
      WHERE pages.title = $4
      LIMIT 1;

      IF NOT FOUND THEN
        BEGIN
          INSERT INTO pages (title)
          VALUES ($4);
        EXCEPTION
        WHEN unique_violation THEN
          /* Let it slide */
        END;
      END IF;
    END IF;

    UPDATE pages
    SET id = $1
      , revision = $2
      , title = $3
      , redirect = $4
      , text = $5
    WHERE pages.title = $3
    RETURNING * INTO page;

    IF page IS null THEN
      INSERT INTO pages (id, revision, title, redirect, text)
      VALUES ($1, $2, $3, $4, $5)
      RETURNING * INTO page;
    ELSE /* Clean the n-gram tables for processing ... */
      DELETE FROM trigrams
      WHERE trigrams.page_id = $1;

      DELETE FROM bigrams
      WHERE bigrams.page_id = $1;

      DELETE FROM unigrams
      WHERE unigrams.page_id = $1;
    END IF;

    RETURN page;
  END;
$body$ LANGUAGE plpgsql;

-- vim: set ft=pgsql:
