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

/* For Wikipedia naming restrictions, see the following article:
 *
 * http://en.wikipedia.org/wiki/Wikipedia:Naming_conventions_(technical_restrictions)
 * ========================================================================== */

CREATE TABLE pages (
	-- Unique identifier, provided by Wikipedia
	id integer,

	-- Wikipedia title of the page
	title varchar(256) PRIMARY KEY,

	-- Redirect to this page
	redirect varchar(256),

	-- Latest revision of the page
	revision integer,

	-- Raw text of the page (useful for simulation)
	text text,

  -- Tokenized sequence of terms in this document
	term_sequence integer[]
);

-- vim: set ft=pgsql et sta ts=2 sw=2:
