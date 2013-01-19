-- Copyright (c) 2013 Dylon Edwards
--
-- Permission is hereby granted, free of charge, to any person
-- obtaining a copy of this software and associated documentation
-- files (the "Software"), to deal in the Software without
-- restriction, including without limitation the rights to use,
-- copy, modify, merge, publish, distribute, sublicense, and/or sell
-- copies of the Software, and to permit persons to whom the
-- Software is furnished to do so, subject to the following
-- conditions:
--
-- The above copyright notice and this permission notice shall be
-- included in all copies or substantial portions of the Software.
--
-- THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
-- EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
-- OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
-- NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
-- HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
-- WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
-- FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
-- OTHER DEALINGS IN THE SOFTWARE.

CREATE FUNCTION insert_or_update_page( pages.title%type
                                     , pages.id%type            = 0
                                     , pages.redirect%type      = NULL
                                     , pages.revision%type      = NULL
                                     , pages.text%type          = NULL
                                     , pages.term_sequence%type = NULL
                                     )
RETURNS void AS
$body$
  DECLARE
    existing_page_id pages.id%type;
  BEGIN
    IF $3 IS NOT NULL THEN
      PERFORM true
      FROM pages
      WHERE title = $3;

      IF NOT FOUND THEN
        BEGIN
          INSERT INTO pages (title)
          VALUES ($3);
        EXCEPTION
        WHEN unique_violation THEN
          -- ignore
        END;
      END IF;
    END IF;

    SELECT id
    INTO existing_page_id
    FROM pages
    WHERE title = $1;

    IF NOT FOUND THEN
      BEGIN
        INSERT INTO pages (title, id, redirect, revision, text, term_sequence)
        VALUES ($1, $2, $3, $4, $5, $6);
      EXCEPTION
      WHEN unique_violation THEN
        PERFORM insert_or_update_page($1, $2, $3, $4, $5, $6);
      END;
    ELSE
      IF existing_page_id IS NOT NULL THEN
        DELETE FROM n_grams
        WHERE page_id = existing_page_id;
      END IF;

      UPDATE pages
      SET id            = $2
        , redirect      = $3
        , revision      = $4
        , text          = $5
        , term_sequence = $6
      WHERE title = $1;
    END IF;
  END;
$body$ LANGUAGE plpgsql VOLATILE;

-- vim: set ft=pgsql et sta ts=2 sw=2:
