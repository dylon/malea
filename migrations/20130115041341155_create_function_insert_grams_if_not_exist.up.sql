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

/** Inserts all the elements of `$1` into the `grams` table, if they do not
 * exist.  Regardless whether the elements existed previously, a set of doubles
 * (id:integer, value:varchar) is returned consisting of the identifier and
 * value of every element of `$1`.
 */
CREATE FUNCTION insert_grams_if_not_exist(varchar[])
RETURNS TABLE (id integer, value varchar) AS
$body$
  DECLARE
    gram_id integer;
    gram_value varchar;
  BEGIN
    FOREACH gram_value IN ARRAY $1
    LOOP
      SELECT grams.id
      INTO gram_id
      FROM grams
      WHERE grams.value = gram_value;

      IF NOT FOUND THEN
        BEGIN
          INSERT INTO grams (value)
          VALUES (gram_value)
          RETURNING grams.id INTO gram_id;
        EXCEPTION
        WHEN unique_violation THEN
          SELECT grams.id
          INTO gram_id
          FROM grams
          WHERE grams.value = gram_value;
        END;
      END IF;

      RETURN QUERY SELECT gram_id AS id, gram_value AS value;
    END LOOP;
  END;
$body$ LANGUAGE plpgsql VOLATILE;

-- vim: set ft=pgsql et sta ts=2 sw=2:
