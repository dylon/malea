;; Copyright (c) 2012 Dylon Edwards
;;
;; Permission is hereby granted, free of charge, to any person obtaining a copy
;; of this software and associated documentation files (the "Software"), to deal
;; in the Software without restriction, including without limitation the rights
;; to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
;; copies of the Software, and to permit persons to whom the Software is
;; furnished to do so, subject to the following conditions:
;;
;; The above copyright notice and this permission notice shall be included in
;; all copies or substantial portions of the Software.
;;
;; THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
;; IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
;; FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
;; AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
;; LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
;; OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
;; SOFTWARE.

(ns malea.test.data)

(def ^:const dictionary [
"drownings" "cockier" "crumbiest" "guillemot" "polls" "formic" "raconteur's"
"dreamer" "turnabout" "maintaining" "councilors" "aphasic's" "barons"
"circulated" "cabana's" "freeload" "vulnerability's" "provincialism"
"detestation's" "pronged" "Georgina" "Nigerien" "defeat" "endurance"
"nourishment's" "tawdrily" "promos" "Jamar's" "stitching" "jerkily" "jerkiness"
"trimester's" "Capek" "Neil's" "tared" "warrantied" "White's" "Zapotec" "crew"
"downside" "dish" "atom's" "stifles" "lavaliere's" "opium" "séance's" "sifting"
"inactivated" "edifice's" "extemporizes" "Mulligan" "Gucci's" "Cuban's" "sue"
"synergies" "mumblers" "mandrels" "deer" "polio's" "Okefenokee"
"transformation's" "slightness's" "Xmas's" "capacity's" "timbers" "Waksman"
"progressively" "Tasman" "mooring" "contravene" "fizzed" "shootout"
"inoculating" "primitively" "copay's" "falsie's" "restructures" "Ireland's"
"hubbub's" "liability's" "rhetoric" "filthy" "instrumentation" "striptease's"
"Cuvier" "transitory" "relieving" "excls" "subdivisions" "Deloris" "shrieking"
"flinch's" "steamed" "commissar" "readdresses" "keel's" "potentate" "journos"
"Brahms" "reviewer's" "luncheon" "slap" "namedrop" "knots" "minim's"
"unaccomplished" "yellowhammers" "Johnathan's" "Bodhisattva" "scrabbler's"
"commons" "rad's" "swankiness" "slapstick's" "wagerer" "confectioner's"
"retouching" "peers" "darner" "thyroid's" "Lloyd" "skulker's" "unrestricted"
"Frenchmen" "balder" "Shea's" "decipher" "esquire" "unkindliest" "Costner"
"unyokes" "disputer" "DOB" "rices" "hoecake" "Reinaldo" "muff's" "roiled"
"Amber's" "grues" "wrestlers" "pencil's" "finagle" "ballsed" "Jules" "notifier"
"Louisa's" "uncolored" "indubitable" "syllable" "expediter" "yang" "Frankel"
"belling" "circumnavigations" "tests" "involuntarily" "blab's" "windflower's"
"delegation" "merchandiser" "cases" "pinewoods" "unfilled" "Suns" "nonspeaking"
"lowlander's" "puller" "fraudsters" "horde" "compunction's" "dewclaw's"
"herdsman" "Fidel" "extrapolation's" "Lenny's" "Avalon" "brake's" "Horne"
"weltered" "accrual" "mugging" "countrified" "maillots" "proprietorship"
"minute's" "Efrain's" "availed" "fattest" "engineer" "wheelchairs"
"unseemliness's" "anachronistically" "slue's" "deformity's" "explosive's"
"bicep" "privatest" "fantasized" "sufferings" "titchy" "blowhards" "first's"
"myxomatosis" "assistance's" "dreamworld's" "subsisting" "expatriation's"
"snipe" "concreting" "anonymity's" "divination" "casehardens" "deadpanning"
"siltier" "perimeter's" "nonfatal" "calibrated" "suggest" "adjoining"
"redounded" "denationalize" "centime" "attacker's" "drams" "hardball" "voter"
"crossovers" "gingered" "plushness's" "bodice" "undervalue" "nationalist"
"clocks" "prearranges" "cabbing" "platters" "recuperating" "debtor"
"disparagement" "plunderer" "knockwursts" "draftsman" "skivers" "eon's"
"announcement's" "prospect's" "adjudge" "Patterson's" "Johnston's" "stammer"
"majorities" "featherbrained" "kayos" "formulator" "fishcake" "bighorn"
"oscillation" "reenlistment's" "downer's" "license" "lubricated" "Jacky"
"Elul's" "pickerel" "none" "vociferously" "arboreal" "RAM" "recessive's"
"ramble" "seaways" "assist" "Ouijas" "bobbysoxer's" "Matt" "colliery" "sundry"
"saddle's" "ballerina" "bluegrass" "Crater's" "rematch's" "czar" "stripes"
"goodhearted" "cobblestones" "walkway's" "Amerindian" "crosschecks" "alkaline"
"Larsen" "valveless" "Chretien's" "Ramirez" "Weinberg" "rubs" "privatizes"
"hairiness" "genetic" "woodpile's" "confidant" "granite's" "Borges" "eroded"
"embezzlement" "abhors" "hamstring's" "Lydian" "pedagogical" "socialize"
"psychotic" "risers" "organist" "repatriation" "nonpolitical" "thrilling"
"musicianship's" "updraft's" "unsatisfied" "Roscoe" "encore's" "antagonism's"
"gestured" "travestied" "meats" "blancmange" "representative's" "bobsled's"
"indicatively" "octavo" "fruits" "squint" "coffeehouse" "schoolmistress's"
"treble's" "screwworms" "holograph's" "reheats" "surfer's" "reconnoiters"
"monodist's" "therapeutics" "ploy" "collocates" "incompatibility" "Kasey"
"atonal" "slowed" "Lucio" "fathered" "posy's" "boil's" "Lelia" "workbasket"
"housetop's" "photostat's" "beware" "likest" "bittern's" "clarioning"
"tenderheartedness's" "waxwings" "intruders" "Makarios" "sulfate" "plover's"
"dipole's" "divorcee" "sedation's" "tinkle's" "disenfranchisement" "avenues"
"wifeliest" "Schwinn's" "peptics" "sickens" "LLD's" "urinates" "raggeder"
"parceled" "makings's" "dispel" "oversubscribed" "crematory" "proforma" "om's"
"overconscientious" "fake" "symbolize" "attributive" "pejoratives" "Mintaka's"
"cake" "Terkel's" "crocodile" "hornpipe" "regression" "CFC's" "plummeted" "rib"
"immures" "duel's" "denominators" "Gehrig" "sturgeons" "vicing" "drift"
"cylinders" "ark's" "pottage's" "undertones" "bestowing" "czarist"
"microphone's" "surplice's" "lipsticks" "tuckering" "sampler's" "ciphered"
"democrat" "outcast" "Arizonan's" "odometer's" "astraddle" "taco's" "sodas"
"archdeacon" "hog's" "shortcakes" "pubs" "together" "reduplication's" "detects"
"Mauryan" "counterpane's" "muskrat" "wobble's" "deplane" "quatrains" "flanges"
"quenching" "conspectus's" "Navajos" "glazed" "abidance's" "amigo's" "tricycles"
"Deleon's" "traumatize" "guise's" "Schwinger's" "scud's" "Marseillaises"
"pealed" "ping's" "workload's" "manipulator's" "TEFL" "procurator's" "fattiness"
"kaffeeklatsch" "jetports" "express" "frailly" "prophet" "pantry's"
"hydrogenous" "scarping" "good's" "lovechild's" "admits" "lavenders" "effigies"
"airing's" "MA's" "perplexity's" "tabooed" "polling" "trespassers" "waives"
"tablecloth" "squishy" "purdah" "deliberation" "inconsiderately" "stuffed"
"appear" "Argentinians" "estrus's" "upstate" "preserves" "sateen" "meadow's"
"velvet's" "waitstaff" "Davies" "proctors" "robbed" "Phrygia's" "pomading"
"gem's" "situate" "gangrene" "value's" "competences" "schmoozer" "stoppers"
"wastebasket" "windowing" "refutes" "conceited" "bootless" "longboat" "serer"
"fronts" "racial" "illustration" "leasing" "teacake" "transsexual's"
"underskirt" "constitutionality's" "vocable's" "dartboard's" "Loewi" "Vlad"
"exigency" "Nichiren's" "Düsseldorf's" "articulating" "waywardness" "gite"
"unicorns" "fiancee" "emcee" "gasps" "birthstones" "COBOLs" "contemplative's"
"lionizes" "cantata" "competing" "conscientiously" "pol's" "prospect" "Sabik's"
"cadence's" "choosers" "sublimate" "outstandingly" "Poconos" "holdup" "Kari"
"Purims" "unwrapped" "Lenard" "array's" "sorrels" "manipulating" "yipped"
"Sodom" "spiteful" "redeployment" "misdeed's" "recces" "retrieval" "challenge's"
"depending" "unlivable" "Elvis" "upthrust" "palisade" "backwards" "whittlers"
"Toynbee's" "cystitis" "Madagascan's" "unconventionally" "crossfire" "radish"
"Daisy" "cirque's" "requiters" "somerset's" "mocked" "censor's" "bulb"
"outspoken" "Xmas" "asylums" "dominates" "gnashes" "supplies" "frailty's"
"crabgrass" "oceanic's" "fiancée" "furriness" "henceforth" "rectories"
"pretexts" "semiotics's" "biddy" "snafu" "repines" "reputes" "Pilate" "hips"
"lutanist" "lames" "inducements" "xxxvii" "transformer's" "metamorphosis's"
"unadorned" "Eurasia's" "redyed" "Darla's" "Chippewa" "horsebox" "apogee's"
"Mmes" "gobs" "suckles" "buttons" "dopers" "kilocycles" "neutral's" "shellfire"
"delinquent's" "subject's" "Menander" "prevention" "litigation's" "backup's"
"cosmopolitan" "spotting" "Trudy" "complaint" "quatrain's" "elite" "facets"
"Hilda's" "acerbates" "PET's" "teaches" "Ozymandias's" "Chipewyan" "edgy"
"converge" "boxer's" "Tsongkhapa" "compacts" "consecration's" "oblations"
"antispasmodics" "mechanically" "bullshitter" "floozy" "offhandedness" "eider"
"maintainers" "desolate" "Vespasian" "passim" "contraband's" "wreaked"
"christen" "resin" "digitizes" "snottiness" "showjumping" "gasworks's"
"inconsideration's" "Iapetus's" "loudmouth's" "nibble's" "Philippians"
"diversities" "metastasizes" "redeeming" "hypertrophied" "pesticides" "skim"
"Rae" "cacophony's" "broilers" "corresponds" "emeralds" "Hamsun"
"tatterdemalion" "America's" "surname" "roister" "unreachable" "slag"
"terrorism's" "monseigneur" "confab" "environmentalists" "segment's"
"untwisting" "calisthenic" "misgoverned" "overarm" "Brahma" "ingestion"
"firer's" "skid" "unzipped" "endures" "muggiest" "decathlete" "parenthesis"
"Aeschylus's" "abstracting" "origami" "rephotograph" "steeple" "tearier" "Italy"
"misconception" "Florida" "orris's" "hairless" "wolfish" "Giuliani" "titan"
"thespians" "ovation" "bestsellers" "basically" "Epson" "lump's" "dissatisfied"
"Serpens" "tackler's" "antivenins" "understand" "exp" "placid" "Ventolin"
"reserve" "acetone's" "overgraze" "beating" "verges" "thornier" "rutabaga"
"procrastination's" "loving" "survives" "jarring" "UN" "delayers" "dieseled"
"narcotization" "reap" "Argentinian's" "Lupus" "threateningly" "ciphers"
"postindustrial" "usurpation" "sisterhood's" "clutched" "nestling" "doomsday"
"outworkers" "Satanist" "sunburning" "sidetracked" "Elijah's" "fatality"
"Dictaphone's" "headdresses" "faithless" "bicameralism" "houseplant" "balloting"
"scoots" "mainstay's" "sequencers" "mistyping" "swarthy" "Narnia" "tubercle's"
"beachcomber's" "garnering" "usefully" "cellar's" "Adelaide" "emigrates" "mot's"
"officeholder's" "ambrosia's" "pooches" "tearoom" "boll's" "straighteners"
"lexer" "concessional" "Havel" "panted" "snowiness" "underage" "uselessness"
"twinkle" "vanquish" "deadbeat" "gauge" "hurdler" "choruses" "publican's"
"Sappho" "Nita's" "indolently" "carpels" "lobotomy" "worthlessness"
"meticulously" "fashionably" "rayon's" "superstates" "simpler" "aviatrixes"
"marine" "tectonic" "blusterer's" "ceasefire's" "tantalizing" "blarneying"
"boxcar" "nonnarcotics" "AD" "wheelbase's" "lifestyle" "whitewalls" "miniseries"
"monstrously" "modesty's" "Bronx's" "slits" "Rayburn's" "strap" "laminate's"
"fleetness's" "Methodism's" "lockjaw" "employed" "economists" "judders"
"leering" "pyloric" "neglected" "hoodwink" "icebreaker's" "pinewood"
"pusillanimity's" "clams" "marshes" "tangle" "custodian" "Veblen's"
"patrimonies" "fillet's" "succumb" "horizontal's" "heartlessness's" "perused"
"implacably" "breakfasts" "sexiness" "Annabelle's" "Chin" "hustling" "unmasked"
"homeworking" "misapprehended" "retribution" "telescopically" "sketcher"
"hydrometer" "confectionery's" "gritted" "retried" "niggardliness's" "jinn"
"chickpea's" "poi's" "taxonomy's" "resignedly" "bards" "odyssey's"
"intensifying" "wimpling" "stool's" "perpendicular's" "leis" "drifted" "swine"
"Venice" "pullout" "timpanist's" "knifed" "Janet" "Tanya's" "gamboled"
"treacherousness's" "grokking" "Irvin's" "bounder" "monopolize" "officialdom's"
"biophysicists" "Garza's" "hectogram's" "perspired" "depraves" "shrimping"
"flaming" "palish" "proverb's" "Caedmon" "entailing" "stoups" "dulcet"
"takeoff's" "legalizes" "sepulcher's" "dismay's" "procuring" "catted" "ship's"
"bassoon" "extremism" "quince's" "herds" "masonic" "SARS" "boyhood" "cementum's"
"Carlos" "wrapper's" "transmogrification's" "introversion's" "enable" "overplay"
"reinvention's" "anatomist" "Eaton's" "fared" "pupating" "requited" "hydrate's"
"Mitterrand" "hue's" "salvo" "requisition" "defacer" "chitterlings" "outbreak's"
"ruddier" "fulminated" "degeneration's" "depots" "chewer's" "Panamanians"
"vociferating" "haversack" "vacillations" "Confucianisms" "practically"
"shelled" "priorities" "baluster" "cue" "stagier" "libeling" "spritzing"
"fibrils" "autocrat's" "undignified" "affirming" "usherette's" "Haiti" "nice"
"idled" "testament's" "shortages" "arbitrage" "allowably" "Truman's" "globs"
"Saroyan" "redecorates" "Savoyard's" "Virgos" "cottoned"
])

(def ^:const xml-document
"<mediawiki xmlns=\"http://www.mediawiki.org/xml/export-0.8/\"
            xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"
            xsi:schemaLocation=\"http://www.mediawiki.org/xml/export-0.8/
                                 http://www.mediawiki.org/xml/export-0.8.xsd\"
            version=\"0.8\"
            xml:lang=\"en\">
  <siteinfo>
    <sitename>Wikipedia</sitename>
    <base>http://en.wikipedia.org/wiki/Main_Page</base>
    <generator>MediaWiki 1.21wmf2</generator>
    <case>first-letter</case>
    <namespaces>
      <namespace key=\"-2\" case=\"first-letter\">Media</namespace>
      <namespace key=\"-1\" case=\"first-letter\">Special</namespace>
      <namespace key=\"0\" case=\"first-letter\" />
      <namespace key=\"1\" case=\"first-letter\">Talk</namespace>
      <namespace key=\"2\" case=\"first-letter\">User</namespace>
      <namespace key=\"3\" case=\"first-letter\">User talk</namespace>
      <namespace key=\"4\" case=\"first-letter\">Wikipedia</namespace>
      <namespace key=\"5\" case=\"first-letter\">Wikipedia talk</namespace>
      <namespace key=\"6\" case=\"first-letter\">File</namespace>
      <namespace key=\"7\" case=\"first-letter\">File talk</namespace>
      <namespace key=\"8\" case=\"first-letter\">MediaWiki</namespace>
      <namespace key=\"9\" case=\"first-letter\">MediaWiki talk</namespace>
      <namespace key=\"10\" case=\"first-letter\">Template</namespace>
      <namespace key=\"11\" case=\"first-letter\">Template talk</namespace>
      <namespace key=\"12\" case=\"first-letter\">Help</namespace>
      <namespace key=\"13\" case=\"first-letter\">Help talk</namespace>
      <namespace key=\"14\" case=\"first-letter\">Category</namespace>
      <namespace key=\"15\" case=\"first-letter\">Category talk</namespace>
      <namespace key=\"100\" case=\"first-letter\">Portal</namespace>
      <namespace key=\"101\" case=\"first-letter\">Portal talk</namespace>
      <namespace key=\"108\" case=\"first-letter\">Book</namespace>
      <namespace key=\"109\" case=\"first-letter\">Book talk</namespace>
      <namespace key=\"446\" case=\"first-letter\">Education Program</namespace>
      <namespace key=\"447\" case=\"first-letter\">Education Program talk</namespace>
      <namespace key=\"710\" case=\"first-letter\">TimedText</namespace>
      <namespace key=\"711\" case=\"first-letter\">TimedText talk</namespace>
    </namespaces>
  </siteinfo>
  <page>
    <title>AccessibleComputing</title>
    <ns>0</ns>
    <id>10</id>
    <redirect title=\"Computer accessibility\" />
    <revision>
      <id>381202555</id>
      <parentid>381200179</parentid>
      <timestamp>2010-08-26T22:38:36Z</timestamp>
      <contributor>
        <username>OlEnglish</username>
        <id>7181920</id>
      </contributor>
      <minor />
      <comment>
        [[Help:Reverting|Reverted]] edits by
        [[Special:Contributions/76.28.186.133|76.28.186.133]]
        ([[User talk:76.28.186.133|talk]]) to last version by Gurch
      </comment>
      <text xml:space=\"preserve\">
        #REDIRECT [[Computer accessibility]] {{R from CamelCase}}
      </text>
      <sha1>lo15ponaybcg2sf49sstw9gdjmdetnk</sha1>
      <model>wikitext</model>
      <format>text/x-wiki</format>
    </revision>
  </page>
  <page>
    <title>Awesomeizer</title>
    <ns>0</ns>
    <id>332</id>
    <revision>
      <id>510480164</id>
      <parentid>502956009</parentid>
      <timestamp>2012-09-02T20:12:02Z</timestamp>
      <contributor>
        <username>Mannanan51</username>
        <id>13937019</id>
      </contributor>
      <comment>added ref</comment>
      <text xml:space=\"preserve\">
        This is a sentence. This is another one from Dr. Insanity!
      </text>
      <sha1>7wfhyuweu40y4zy1glzssc97kpi81w3</sha1>
      <model>wikitext</model>
      <format>text/x-wiki</format>
    </revision>
  </page>
</mediawiki>")

