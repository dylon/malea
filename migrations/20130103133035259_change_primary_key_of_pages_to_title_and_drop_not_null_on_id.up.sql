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

ALTER TABLE trigrams
DROP CONSTRAINT trigrams_page_id_fkey;

ALTER TABLE bigrams
DROP CONSTRAINT bigrams_page_id_fkey;

ALTER TABLE unigrams
DROP CONSTRAINT unigrams_page_id_fkey;

CREATE UNIQUE INDEX index_pages_on_unique_id
ON pages (id);

ALTER TABLE pages
DROP CONSTRAINT pages_pkey,
ADD CONSTRAINT pages_pkey
  PRIMARY KEY
  USING INDEX index_pages_on_unique_title,
ALTER COLUMN id
  DROP NOT NULL,
ALTER COLUMN revision
  DROP NOT NULL,
DROP CONSTRAINT fk_redirect_references_pages_title,
ADD CONSTRAINT fk_redirect_references_pages_title
  FOREIGN KEY (redirect)
  REFERENCES pages (title)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE unigrams
ADD CONSTRAINT unigrams_page_id_fkey
FOREIGN KEY (page_id)
REFERENCES pages (id)
ON DELETE CASCADE;

ALTER TABLE bigrams
ADD CONSTRAINT bigrams_page_id_fkey
FOREIGN KEY (page_id)
REFERENCES pages (id)
ON DELETE CASCADE;

ALTER TABLE trigrams
ADD CONSTRAINT trigrams_page_id_fkey
FOREIGN KEY (page_id)
REFERENCES pages (id)
ON DELETE CASCADE;

-- vim: set ft=pgsql:
