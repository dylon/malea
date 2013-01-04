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

/*ALTER TABLE grams DROP CONSTRAINT grams_value_key;*//* Keep this one */

ALTER TABLE grams ALTER COLUMN value DROP NOT NULL;

ALTER TABLE unigrams
  DROP CONSTRAINT unigrams_page_id_gram_1_id_key,
  DROP CONSTRAINT unigrams_frequency_check,
  DROP CONSTRAINT unigrams_gram_1_id_fkey,
  DROP CONSTRAINT unigrams_page_id_fkey,
  ALTER COLUMN frequency DROP NOT NULL,
  ALTER COLUMN frequency DROP DEFAULT;
DROP INDEX index_unigrams_on_gram_1_id;
DROP INDEX index_unigrams_on_page_id;

ALTER TABLE bigrams
  DROP CONSTRAINT bigrams_page_id_gram_1_id_gram_2_id_key,
  DROP CONSTRAINT bigrams_frequency_check,
  DROP CONSTRAINT bigrams_gram_1_id_fkey,
  DROP CONSTRAINT bigrams_gram_2_id_fkey,
  DROP CONSTRAINT bigrams_page_id_fkey,
  ALTER COLUMN frequency DROP NOT NULL,
  ALTER COLUMN frequency DROP DEFAULT;
DROP INDEX index_bigrams_on_gram_1_id;
DROP INDEX index_bigrams_on_gram_2_id;
DROP INDEX index_bigrams_on_page_id;

ALTER TABLE trigrams
  DROP CONSTRAINT trigrams_page_id_gram_1_id_gram_2_id_gram_3_id_key,
  DROP CONSTRAINT trigrams_frequency_check,
  DROP CONSTRAINT trigrams_gram_1_id_fkey,
  DROP CONSTRAINT trigrams_gram_2_id_fkey,
  DROP CONSTRAINT trigrams_gram_3_id_fkey,
  DROP CONSTRAINT trigrams_page_id_fkey,
  ALTER COLUMN frequency DROP NOT NULL,
  ALTER COLUMN frequency DROP DEFAULT;
DROP INDEX index_trigrams_on_gram_1_id;
DROP INDEX index_trigrams_on_gram_2_id;
DROP INDEX index_trigrams_on_gram_3_id;
DROP INDEX index_trigrams_on_page_id;

ALTER TABLE pages DROP CONSTRAINT fk_redirect_references_pages_title;
DROP INDEX index_pages_on_unique_id;
DROP INDEX index_pages_on_unique_revision;
DROP INDEX index_pages_on_redirect;

-- vim: set ft=pgsql:
