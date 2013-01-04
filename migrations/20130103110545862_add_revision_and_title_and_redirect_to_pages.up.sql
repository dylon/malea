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

ALTER TABLE pages
ADD COLUMN revision integer;

ALTER TABLE pages
ADD COLUMN title varchar(255);

ALTER TABLE pages
ADD COLUMN redirect varchar(255);

UPDATE pages
SET revision = id
	, title = 'Default Title for ' || id;

ALTER TABLE pages
ALTER COLUMN revision
SET NOT NULL;

CREATE UNIQUE INDEX index_pages_on_unique_revision
ON pages (revision);

ALTER TABLE pages
ALTER COLUMN title
SET NOT NULL;

CREATE UNIQUE INDEX index_pages_on_unique_title
ON pages (title);

ALTER TABLE pages
ADD CONSTRAINT fk_redirect_references_pages_title
FOREIGN KEY (redirect)
REFERENCES pages (title);

-- vim: set ft=pgsql:
