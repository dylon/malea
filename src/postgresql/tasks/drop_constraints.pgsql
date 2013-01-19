/* Drop N-Gram Constraints
 * ======================= */

DROP INDEX index_n_grams_on_gram_id;
DROP INDEX index_n_grams_on_page_id;
DROP INDEX index_n_grams_on_parent_id;

ALTER TABLE n_grams
	ALTER COLUMN frequency DROP NOT NULL,
	ALTER COLUMN frequency DROP DEFAULT,
	DROP CONSTRAINT validate_page_id_and_parent_id,
	DROP CONSTRAINT validate_frequency,
	DROP CONSTRAINT n_grams_gram_id_fkey,
	DROP CONSTRAINT n_grams_page_id_fkey,
	DROP CONSTRAINT n_grams_parent_id_fkey;

/* Drop Gram Constraints
 * ===================== */

DROP INDEX index_grams_on_unique_id;

ALTER TABLE grams
ALTER COLUMN id
DROP NOT NULL;

/* Drop Page Constraints
 * ===================== */

DROP INDEX index_pages_on_redirect;
DROP INDEX index_pages_on_unique_revision;
DROP INDEX index_pages_on_unique_id;

ALTER TABLE pages
	DROP CONSTRAINT validate_id_and_revision,
	DROP CONSTRAINT pages_redirect_fkey;

