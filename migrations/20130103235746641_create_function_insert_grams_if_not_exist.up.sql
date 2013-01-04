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

-- =============================================================================
-- NOTE: This migration failed to run via ragtime, so I copypasta'd it to the
-- Postgres console.
-- =============================================================================

CREATE OR REPLACE FUNCTION insert_grams_if_not_exist(varchar(255)[])
RETURNS integer AS $BODY$
  DECLARE
    inserted integer := 0;
    gram varchar(255);
  BEGIN
    FOREACH gram IN ARRAY $1 LOOP
      PERFORM true
      FROM grams
      WHERE value = gram
      LIMIT 1;

      IF NOT FOUND THEN
        BEGIN
          INSERT INTO grams (value)
          VALUES (gram);

          inserted = inserted + 1;
        EXCEPTION
        WHEN OTHERS THEN
          /* Can't stop this!!! */
        END;
      END IF;
    END LOOP;

    RETURN inserted;
  END;
$BODY$ LANGUAGE plpgsql;

-- vim: set ft=pgsql:
