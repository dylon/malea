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

/* Pages
 * ========================================================================== */

CREATE INDEX index_pages_on_redirect
ON pages (redirect)
WHERE redirect IS NOT NULL;

CREATE UNIQUE INDEX index_pages_on_unique_revision
ON pages (revision);

CREATE UNIQUE INDEX index_pages_on_unique_id
ON pages (id);

INSERT INTO pages (title)
SELECT redirect FROM pages AS invalid_pages
WHERE invalid_pages.redirect IS NOT NULL
  AND NOT EXISTS (
    SELECT true
    FROM pages AS valid_pages
    WHERE valid_pages.title = invalid_pages.redirect
  );

ALTER TABLE pages
ADD CONSTRAINT fk_redirect_references_pages_title
FOREIGN KEY (redirect)
REFERENCES pages (title)
ON UPDATE CASCADE
ON DELETE CASCADE;

/* Trigrams
 * ========================================================================== */

CREATE INDEX index_trigrams_on_page_id
ON trigrams (page_id);

CREATE INDEX index_trigrams_on_gram_3_id
ON trigrams (gram_3_id);

CREATE INDEX index_trigrams_on_gram_2_id
ON trigrams (gram_2_id);

CREATE INDEX index_trigrams_on_gram_1_id
ON trigrams (gram_1_id);

ALTER TABLE trigrams
  ALTER COLUMN frequency
  SET DEFAULT 0,

  ALTER COLUMN frequency
  SET NOT NULL,

/* Bigrams
 * ========================================================================== */



/* Unigrams
 * ========================================================================== */



-- vim: set ft=pgsql:
