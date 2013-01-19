/* Restore Page Constraints
 * ======================== */

ALTER TABLE pages
	ADD CONSTRAINT pages_redirect_fkey
	FOREIGN KEY (redirect)
	REFERENCES pages (title)
	ON DELETE CASCADE,

	ADD CONSTRAINT validate_id_and_revision
	CHECK (id IS NULL AND revision IS NULL
		OR id IS NOT NULL AND revision IS NOT NULL);

CREATE UNIQUE INDEX index_pages_on_unique_id
ON pages (id);

CREATE UNIQUE INDEX index_pages_on_unique_revision
ON pages (revision);

CREATE INDEX index_pages_on_redirect
ON pages (redirect);

/* Restore Gram Constraints
 * ======================== */

ALTER TABLE grams
ALTER COLUMN id
SET NOT NULL;

CREATE UNIQUE INDEX index_grams_on_unique_id
ON grams (id);

/* Restore N-Gram Constraints
 * ========================== */

ALTER TABLE n_grams
	ADD CONSTRAINT n_grams_parent_id_fkey
	FOREIGN KEY (parent_id)
	REFERENCES n_grams (id)
	ON DELETE CASCADE,

	ADD CONSTRAINT n_grams_page_id_fkey
	FOREIGN KEY (page_id)
	REFERENCES pages (id)
	ON DELETE CASCADE,

	ADD CONSTRAINT n_grams_gram_id_fkey
	FOREIGN KEY (gram_id)
	REFERENCES grams (id)
	ON DELETE CASCADE,

	ADD CONSTRAINT validate_frequency
	CHECK (frequency > 0),

	ADD CONSTRAINT validate_page_id_and_parent_id
	CHECK (page_id IS NOT NULL AND parent_id IS NULL
		OR page_id IS NULL AND parent_id IS NOT NULL),

	ALTER COLUMN frequency
	SET NOT NULL,

	ALTER COLUMN frequency
	SET DEFAULT 1;

CREATE INDEX index_n_grams_on_gram_id
ON n_grams (gram_id);

CREATE INDEX index_n_grams_on_page_id
ON n_grams (page_id);

CREATE INDEX index_n_grams_on_parent_id
ON n_grams (parent_id);

