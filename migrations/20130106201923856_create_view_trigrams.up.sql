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

CREATE VIEW trigrams AS
SELECT trigram_1.page_id
     , gram_1.value AS gram_1
     , gram_2.value AS gram_2
     , gram_3.value AS gram_3
     , trigram_3.frequency
FROM n_grams AS trigram_1
JOIN n_grams AS trigram_2
  ON trigram_2.parent_id = trigram_1.id
JOIN n_grams AS trigram_3
  ON trigram_3.parent_id = trigram_2.id
LEFT JOIN grams AS gram_1
  ON gram_1.id = trigram_1.gram_id
LEFT JOIN grams AS gram_2
  ON gram_2.id = trigram_2.gram_id
LEFT JOIN grams AS gram_3
  ON gram_3.id = trigram_3.gram_id;

-- vim: set ft=pgsql et sta ts=2 sw=2:
