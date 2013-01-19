/* Clean Pages
 * =========== */

DELETE FROM pages
WHERE id IS NULL AND revision IS NOT NULL
	OR id IS NOT NULL AND revision IS NULL
	OR NOT EXISTS (
		SELECT true
		FROM pages AS others
		WHERE others.title <> pages.redirect
			OR others.id IS NOT NULL AND pages.id IS NULL AND others.title = pages.title
			OR others.id = pages.id AND others.title <> pages.title
			OR others.id <> pages.id AND others.title = pages.title
			OR others.id IS NULL AND others.revision IS NOT NULL
			OR others.id IS NOT NULL AND others.revision IS NULL
	);

/* Clean Grams
 * =========== */

DELETE FROM grams
WHERE id IS NULL;

/* Clean N-Grams
 * ============= */

DELETE FROM n_grams
WHERE page_id IS NULL AND parent_id IS NULL
	OR page_id IS NOT NULL AND parent_id IS NOT NULL
	OR frequency IS NULL
	OR frequency < 1
	OR NOT EXISTS (
	 	SELECT true
	 	FROM grams
	 	WHERE grams.id = n_grams.gram_id
	)
	OR NOT EXISTS (
	 	SELECT true
	 	FROM pages
	 	WHERE pages.id = n_grams.page_id
	)
	OR NOT EXISTS (
		SELECT true
		FROM n_grams AS parent_n_grams
		WHERE parent_n_grams.id = n_grams.parent_id
	);

