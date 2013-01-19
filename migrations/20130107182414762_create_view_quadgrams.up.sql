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

CREATE VIEW quadgrams AS
SELECT quadgram_1.page_id
     , gram_1.value AS gram_1
     , gram_2.value AS gram_2
     , gram_3.value AS gram_3
     , gram_4.value AS gram_4
     , quadgram_4.frequency
FROM n_grams AS quadgram_1
JOIN n_grams AS quadgram_2
  ON quadgram_2.parent_id = quadgram_1.id
JOIN n_grams AS quadgram_3
  ON quadgram_3.parent_id = quadgram_2.id
JOIN n_grams AS quadgram_4
  ON quadgram_4.parent_id = quadgram_3.id
LEFT JOIN grams AS gram_1
  ON gram_1.id = quadgram_1.gram_id
LEFT JOIN grams AS gram_2
  ON gram_2.id = quadgram_2.gram_id
LEFT JOIN grams AS gram_3
  ON gram_3.id = quadgram_3.gram_id
LEFT JOIN grams AS gram_4
  ON gram_4.id = quadgram_4.gram_id;

-- vim: set ft=pgsql et sta ts=2 sw=2:
