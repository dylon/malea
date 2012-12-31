-- Copyright (c) 2012 Dylon Edwards
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

CREATE TABLE IF NOT EXISTS trigrams (
	page_id integer NOT NULL REFERENCES pages (id) ON DELETE CASCADE,
	gram_1_id integer REFERENCES grams (id) ON DELETE CASCADE,
	gram_2_id integer REFERENCES grams (id) ON DELETE CASCADE,
	gram_3_id integer REFERENCES grams (id) ON DELETE CASCADE,
	frequency integer NOT NULL DEFAULT 0 CHECK (frequency >= 0),
	PRIMARY KEY (page_id, gram_1_id, gram_2_id, gram_3_id)
);

DROP INDEX IF EXISTS index_trigrams_on_page_id;
CREATE INDEX index_trigrams_on_page_id
ON trigrams (page_id);

DROP INDEX IF EXISTS index_trigrams_on_gram_1_id;
CREATE INDEX index_trigrams_on_gram_1_id
ON trigrams (gram_1_id);

DROP INDEX IF EXISTS index_trigrams_on_gram_2_id;
CREATE INDEX index_trigrams_on_gram_2_id
ON trigrams (gram_2_id);

DROP INDEX IF EXISTS index_trigrams_on_gram_3_id;
CREATE INDEX index_trigrams_on_gram_3_id
ON trigrams (gram_3_id);

-- vim: set ft=pgsql:
