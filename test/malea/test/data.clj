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
  <page>
    <title>Algorithm</title>
    <ns>0</ns>
    <id>775</id>
    <revision>
      <id>520113851</id>
      <parentid>520089304</parentid>
      <timestamp>2012-10-27T13:55:46Z</timestamp>
      <contributor>
        <username>Wvbailey</username>
        <id>753750</id>
      </contributor>
      <comment>Undid revision 520089304 by [[Special:Contributions/85.101.203.163|85.101.203.163]] ([[User talk:85.101.203.163|talk]])</comment>
      <text xml:space=\"preserve\">  Image:Euclid flowchart 1.png|thumb|lright| [[Flow chart]] of an algorithm ([[Euclid's algorithm]]) for calculating the greatest common divisor (g.c.d.) of two numbers ''a'' and ''b'' in locations named A and B. The algorithm proceeds by successive subtractions in two loops: IF the test B ≥ A yields &quot;yes&quot; (or true) (more accurately the ''number'' ''b'' in location B is greater than or equal to the ''number'' ''a'' in location A) THEN the algorithm specifies B ← B − A (meaning the number ''b'' − ''a'' replaces the old ''b''). Similarly IF A &gt; B THEN A ← A − B. The process terminates when (the contents of) B is 0, yielding the g.c.d. in A. (Algorithm derived from Scott 2009:13; symbols and drawing style from Tausworthe 1977).]]

In [[mathematics]] and [[computer science]], an '''algorithm''' {{IPAc-en|audio=en-us-algorithm.ogg|ˈ|æ|l|ɡ|ə|r|ɪ|ð|əm}} (originating from the famous Persian mathematician [[Muḥammad ibn Mūsā al-Khwārizmī]]) is a step-by-step procedure for calculations.  Algorithms are used for [[calculation]], [[data processing]], and [[automated reasoning]].

More precisely, an algorithm is an [[effective method]] expressed as a [[wikt:Special:Search/finite|finite]] list&lt;ref&gt;&quot;Any classical mathematical algorithm, for example, can be described in a finite number of English words&quot; (Rogers 1987:2).&lt;/ref&gt; of well-defined instructions&lt;ref&gt;Well defined with respect to the agent that executes the algorithm: &quot;There is a computing agent, usually human, which can react to the instructions and carry out the computations&quot; (Rogers 1987:2).&lt;/ref&gt; for calculating a [[Function (mathematics)|function]].&lt;ref&gt;&quot;an algorithm is a procedure for computing a ''function'' (with respect to some chosen notation for integers) ... this limitation (to numerical functions) results in no loss of generality&quot;, (Rogers 1987:1).&lt;/ref&gt;  Starting from an initial state and initial input (perhaps [[null string|empty]]),&lt;ref&gt;&quot;An algorithm has [[zero]] or more inputs, i.e., [[quantity|quantities]] which are given to it initially before the algorithm begins&quot; (Knuth 1973:5).&lt;/ref&gt; the instructions describe a [[computation]] that, when [[Execution (computing)|executed]], will proceed through a finite &lt;ref&gt;&quot;A procedure which has all the characteristics of an algorithm except that it possibly lacks finiteness may be called a 'computational method'&quot; (Knuth 1973:5).&lt;/ref&gt; number of well-defined successive states, eventually producing &quot;output&quot;&lt;ref&gt;&quot;An algorithm has one or more outputs, i.e. quantities which have a specified relation to the inputs&quot; (Knuth 1973:5).&lt;/ref&gt; and terminating at a final ending state. The transition from one state to the next is not necessarily [[deterministic]]; some algorithms, known as [[randomized algorithms]], incorporate random input.&lt;ref&gt;Whether or not a process with random interior processes (not including the input) is an algorithm is debatable. Rogers opines that: &quot;a computation is carried out in a discrete stepwise fashion, without use of continuous methods or analogue devices . . . carried forward deterministically, without resort to random methods or devices, e.g., dice&quot; Rogers 1987:2).&lt;/ref&gt;

A partial formalization of the concept began with attempts to solve the [[Entscheidungsproblem]]  the &quot;decision problem&quot;) posed by [[David Hilbert]] in 1928. Subsequent formalizations were framed as attempts to define &quot;[[effective calculability]]&quot;&lt;ref&gt;Kleene 1943 in Davis 1965:274&lt;/ref&gt; or &quot;effective method&quot;;&lt;ref&gt;Rosser 1939 in Davis 1965:225&lt;/ref&gt; those formalizations included the [[Kurt Gödel|Gödel]]–[[Jacques Herbrand|Herbrand]]–[[Stephen Cole Kleene|Kleene]] [[Recursion (computer science)|recursive function]]s of 1930, 1934 and 1935, [[Alonzo Church]]'s [[lambda calculus]] of 1936, [[Emil Post]]'s &quot;[[Formulation 1]]&quot; of 1936, and [[Alan Turing]]'s [[Turing machines]] of 1936–7 and 1939. Giving a formal definition of algorithms, corresponding to the intuitive notion, remains a challenging problem.&lt;ref&gt;{{cite book|first1=Yiannis N.|last1=Moschovakis|chapter=What is an algorithm?|title=Mathematics Unlimited — 2001 and beyond|editor1-first=B.|editor1-last=Engquist|editor2-first=W.|editor2-last=Schmid|publisher=Springer|year=2001|pages=919–936 (Part II)|url=http://citeseer.ist.psu.edu/viewdoc/summary?doi=10.1.1.32.8093}}&lt;/ref&gt;

== Informal definition ==
:''For a detailed presentation of the various points of view around the definition of &quot;algorithm&quot; see [[Algorithm characterizations]]. For examples of simple addition algorithms specified in the detailed manner described in Algorithm characterizations, see [[Algorithm examples]].''
While there is no generally accepted ''formal'' definition of &quot;algorithm,&quot; an informal definition could be &quot;a set of rules that precisely defines a sequence of operations.&quot;&lt;ref&gt;Stone 1973:4&lt;/ref&gt; For some people, a program is only an algorithm if it stops eventually; for others, a program is only an algorithm if it stops before a given number of calculation steps.&lt;ref&gt;Stone simply requires that &quot;it must terminate in a finite number of steps&quot; (Stone 1973:7–8).&lt;/ref&gt;

A prototypical example of an algorithm is [[Euclid's algorithm]] to determine the maximum common divisor of two integers; an example (there are others) is described by the [[flow chart]] above and as an example in a later section.

{{Harvtxt|Boolos|Jeffrey|1974, 1999}} offer an informal meaning of the word in the following quotation:

&lt;blockquote&gt;No human being can write fast enough, or long enough, or small enough† ( †&quot;smaller and smaller without limit ...you'd be trying to write on molecules, on atoms, on electrons&quot;) to list all members of an enumerably infinite set by writing out their names, one after another, in some notation. But humans can do something equally useful, in the case of certain enumerably infinite sets: They can give '''explicit instructions for determining the ''n''th member of the set''', for arbitrary finite ''n''. Such instructions are to be given quite explicitly, in a form in which '''they could be followed by a computing machine''', or by a '''human who is capable of carrying out only very elementary operations on symbols.'''&lt;ref&gt;Boolos and Jeffrey 1974,1999:19&lt;/ref&gt;&lt;/blockquote&gt;

The term &quot;enumerably infinite&quot; means &quot;countable using integers perhaps extending to infinity.&quot; Thus Boolos and Jeffrey are saying that an algorithm implies instructions for a process that &quot;creates&quot; output integers from an ''arbitrary'' &quot;input&quot; integer or integers that, in theory, can be chosen from 0 to infinity. Thus an algorithm can be an algebraic equation such as '''y = m + n'''—two arbitrary &quot;input variables&quot; '''m''' and '''n''' that produce an output '''y'''. But various authors' attempts to define the notion indicate that the word implies much more than this, something on the order of (for the addition example):
:Precise instructions  in language understood by &quot;the computer&quot;)&lt;ref&gt;cf Stone 1972:5&lt;/ref&gt; for a fast, efficient, &quot;good&quot;&lt;ref&gt;Knuth 1973:7 states: &quot;In practice we not only want algorithms, we want ''good'' agorithms ... one criterion of goodness is the length of time taken to perform the algorithm ... other criteria are the adaptability of the algorithm to computers, its simplicty and elegance, etc.&quot;&lt;/ref&gt; process that specifies the &quot;moves&quot; of &quot;the computer&quot; (machine or human, equipped with the necessary internally contained information and capabilities)&lt;ref&gt;cf Stone 1973:6&lt;/ref&gt; to find, decode, and then process arbitrary input integers/symbols '''m''' and '''n''', symbols '''+''' and '''=''' ... and &quot;effectively&quot;&lt;ref&gt;Stone 1973:7–8 states that there must be &quot;a procedure that a robot [i.e. computer] can follow in order to determine pecisely how to obey the instruction&quot;. Stone adds finiteness of the process, and definiteness (having no ambiguity in the instructions) to this definition.&lt;/ref&gt; produce, in a &quot;reasonable&quot; time,&lt;ref&gt;Knuth, loc. cit&lt;/ref&gt; output-integer '''y''' at a specified place and in a specified format.

The concept of ''algorithm'' is also used to define the notion of [[decidability (logic)|decidability]]. That notion is central for explaining how [[formal system]]s come into being starting from a small set of [[axiom]]s and rules. In [[logic]], the time that an algorithm requires to complete cannot be measured, as it is not apparently related with our customary physical dimension. From such uncertainties, that characterize ongoing work, stems the unavailability of a definition of ''algorithm'' that suits both concrete (in some sense) and abstract usage of the term.

== Formalization ==
&lt;!-- If you change this heading's title, [[Computer program]] links here. --&gt;
Algorithms are essential to the way computers process data. Many [[computer program]]s contain algorithms that detail the specific instructions a computer should perform (in a specific order) to carry out a specified task, such as calculating employees' paychecks or printing students' report cards. Thus, an algorithm can be considered to be any sequence of operations that can be simulated by a [[Turing completeness|Turing-complete]] system. Authors who assert this thesis include Minsky (1967), Savage (1987) and Gurevich (2000):
&lt;blockquote&gt; Minsky: &quot;But we will also maintain, with Turing . . . that any procedure which could &quot;naturally&quot; be called effective, can in fact be realized by a (simple) machine. Although this may seem extreme, the arguments . . . in its favor are hard to refute&quot;.&lt;ref name=&quot;Minsky 1967:105&quot;&gt;Minsky 1967:105&lt;/ref&gt;&lt;/blockquote&gt;

 &lt;blockquote&gt;Gurevich: &quot;...Turing's informal argument in favor of his thesis justifies a stronger thesis: every algorithm can be simulated by a Turing machine ... according to Savage [1987], an algorithm is a computational process defined by a Turing machine&quot;.&lt;ref&gt;Gurevich 2000:1, 3&lt;/ref&gt;&lt;/blockquote&gt;

Typically, when an algorithm is associated with processing information, data is read from an input source, written to an output device, and/or stored for further processing. Stored data is regarded as part of the internal state of the entity performing the algorithm. In practice, the state is stored in one or more [[data structure]]s.

For some such computational process, the algorithm must be rigorously defined: specified in the way it applies in all possible circumstances that could arise. That is, any conditional steps must be systematically dealt with, case-by-case; the criteria for each case must be clear (and computable).

Because an algorithm is a precise list of precise steps, the order of computation will always be critical to the functioning of the algorithm. Instructions are usually assumed to be listed explicitly, and are described as starting &quot;from the top&quot; and going &quot;down to the bottom&quot;, an idea that is described more formally by ''[[control flow|flow of control]]''.

So far, this discussion of the formalization of an algorithm has assumed the premises of [[imperative programming]]. This is the most common conception, and it attempts to describe a task in discrete, &quot;mechanical&quot; means. Unique to this conception of formalized algorithms is the [[assignment operation]], setting the value of a variable. It derives from the intuition of &quot;[[memory]]&quot; as a scratchpad. There is an example below of such an assignment.

For some alternate conceptions of what constitutes an algorithm see [[functional programming]] and [[logic programming]].

=== Expressing algorithms ===
Algorithms can be expressed in many kinds of notation, including [[natural language]]s, [[pseudocode]], [[flowchart]]s, [[programming language]]s or [[control table]]s (processed by [[Interpreter (computing)|interpreters]]). Natural language expressions of algorithms tend to be verbose and ambiguous, and are rarely used for complex or technical algorithms. Pseudocode, flowcharts and control tables are structured ways to express algorithms that avoid many of the ambiguities common in natural language statements. Programming languages are primarily intended for expressing algorithms in a form that can be executed by a computer, but are often used as a way to define or document algorithms.

There is a wide variety of representations possible and one can express a given [[Turing machine]] program as a sequence of machine tables (see more at [[finite state machine]], [[state transition table]] and [[control table]]), as flowcharts (see more at [[state diagram]]), or as a form of rudimentary [[machine code]] or [[assembly code]] called &quot;sets of quadruples&quot; (see more at [[Turing machine]]).

Representations of algorithms can be classed into three accepted levels of Turing machine description:&lt;ref&gt;Sipser 2006:157&lt;/ref&gt;
*'''1 High-level description''':
:: &quot;...prose to describe an algorithm, ignoring the implementation details. At this level we do not need to mention how the machine manages its tape or head.&quot;
*'''2 Implementation description''':
:: &quot;...prose used to define the way the Turing machine uses its head and the way that it stores data on its tape. At this level we do not give details of states or transition function.&quot;
*'''3 Formal description''':
:: Most detailed, &quot;lowest level&quot;, gives the Turing machine's &quot;state table&quot;.

:''For an example of the simple algorithm &quot;Add m+n&quot; described in all three levels see [[Algorithm examples]].''

== Implementation ==
Most algorithms are intended to be implemented as [[computer programs]]. However, algorithms are also implemented by other means, such as in a biological [[neural network]] (for example, the [[human brain]] implementing [[arithmetic]] or an insect looking for food), in an [[electrical circuit]], or in a mechanical device.

== Computer algorithms ==
[[File:Euclid's algorithm structured blocks 1.png|thumb|right| Flowchart examples of the canonical [[Structured program theorem|Böhm-Jacopini structures]]: the SEQUENCE (rectangles descending the page), the WHILE-DO and the IF-THEN-ELSE. The three structures are made of the primitive conditional GOTO (IF ''test''=true THEN GOTO step xxx) (a diamond), the unconditional GOTO (rectangle), various assignment operators (rectangle), and HALT (rectangle). Nesting of these structures inside assignment-blocks result in complex diagrams (cf Tausworthe 1977:100,114).]]

In [[computer systems]], an algorithm is basically an instance of [[logic]] written in [[software]] by software developers to be effective for the intended &quot;target&quot; computer(s), in order for the target machines to produce ''output'' from given ''input'' (perhaps null).

'''&quot;Elegant&quot; (compact) programs, &quot;good&quot; (fast) programs ''': The notion of &quot;simplicity and elegance&quot; appears informally in Knuth and precisely in Chaitin:
:Knuth: &quot;. . .we want ''good'' algorithms in some loosely defined aesthetic sense. One criterion . . . is the length of time taken to perform the algorithm . . .. Other criteria are adaptability of the algorithm to computers, its simplicity and elegance, etc&quot;&lt;ref&gt;Knuth 1973:7&lt;/ref&gt;

:Chaitin: &quot; . . . a program is 'elegant,' by which I mean that it's the smallest possible program for producing the output that it does&quot;&lt;ref&gt;Chaitin 2005:32&lt;/ref&gt;

Chaitin prefaces his definition with: &quot;I'll show you can't prove that a program is 'elegant'&quot;—such a proof would solve the [[Halting problem]] (ibid).

'''Algorithm versus function computable by an algorithm''': For a given function multiple algorithms may exist. This will be true, even without expanding the available instruction set available to the programmer. Rogers observes that &quot;It is . . . important to distinguish between the notion of ''algorithm'', i.e. procedure and the notion of ''function computable by algorithm'', i.e. mapping yielded by procedure. The same function may have several different algorithms&quot;.&lt;ref&gt;Rogers 1987:1–2&lt;/ref&gt;

Unfortunately there may be a tradeoff between goodness (speed) and elegance (compactness)—an elegant program may take more steps to complete a computation than one less elegant. An example of using Euclid's algorithm will be shown below.

'''Computers (and computors), models of computation''': A computer  or human &quot;computor&quot;&lt;ref&gt;In his essay &quot;Calculations by Man and Machine: Conceptual Analysis&quot; Seig 2002:390 credits this distinction to Robin Gandy, cf Wilfred Seig, et. al., 2002 ''Reflections on the foundations of mathematics: Essays in honor of Solomon Feferman'', Association for Symbolic Logic, A. K Peters Ltd, Natick, MA.&lt;/ref&gt;) is a restricted type of machine, a &quot;discrete deterministic mechanical device&quot;&lt;ref&gt;cf Gandy 1980:126, Robin Gandy ''Church's Thesis and Principles for Mechanisms'' appearing on pp. 123–148 in J. Barwise et. al. 1980 ''The Kleene Symposium'', North-Holland Publishing Company.&lt;/ref&gt; that blindly follows its instructions.&lt;ref&gt;A &quot;robot&quot;: &quot;A computer is a robot that will perform any task that can be described as a sequence of instructions&quot; cf Stone 1972:3&lt;/ref&gt; Melzak's and Lambek's primitive models&lt;ref&gt;Lambek’s &quot;abacus&quot; is a &quot;countably infinite number of locations (holes, wires etc.) together with an unlimited supply of counters (pebbles, beads, etc). The locations are distinguishable, the counters are not&quot;. The holes will have unlimited capacity, and standing by is an agent who understands and is able to carry out the list of instructions&quot; (Lambek 1961:295). Lambek references Melzak who defines his Q-machine as &quot;an indefinitely large number of locations . . . an indefinitely large supply of counters distributed among these locations, a program, and an operator whose sole purpose is to carry out the program&quot; (Melzak 1961:283). B-B-J (loc. cit.) add the stipulation that the holes are &quot;capable of holding any number of stones&quot; (p. 46). Both Melzak and Lambek appear in ''The Canadian Mathematical Bulletin'', vol. 4, no. 3, September 1961.&lt;/ref&gt; reduced this notion to four elements: (i) discrete, distinguishable ''locations'', (ii) discrete, indistinguishable ''counters''&lt;ref&gt;If no confusion will result, the word &quot;counters&quot; can be dropped, and a location can be said to contain a single &quot;number&quot;.&lt;/ref&gt; (iii) an agent, and (iv) a list of instructions that are ''effective'' relative to the capability of the agent.&lt;ref&gt;&quot;We say that an instruction is ''effective'' if there is a procedure that the robot can follow in order to determine precisely how to obey the instruction&quot; (Stone 1972:6)&lt;/ref&gt;

Minsky describes a more congenial variation of Lambek's &quot;abacus&quot; model in his &quot;Very Simple Bases for [[Computability]]&quot;.&lt;ref&gt;cf Minsky 1967: Chapter 11 &quot;Computer models&quot; and Chapter 14 &quot;Very Simple Bases for Computability&quot; pp. 255–281 in particular&lt;/ref&gt; [[Minsky machine|Minsky's machine]] proceeds sequentially through its five (or six depending on how one counts) instructions unless either a conditional IF–THEN GOTO or an unconditional GOTO changes program flow out of sequence. Besides HALT, Minsky's machine includes three ''assignment'' (replacement, substitution)&lt;ref&gt;cf Knuth 1973:3.&lt;/ref&gt; operations: ZERO (e.g. the contents of location replaced by 0: L ← 0), SUCCESSOR (e.g. L ← L+1), and DECREMENT (e.g. L ← L − 1).&lt;ref&gt;But always preceded by IF–THEN to avoid improper subtraction.&lt;/ref&gt; Rarely will a programmer have to write &quot;code&quot; with such a limited instruction set. But Minsky shows (as do Melzak and Lambek) that his machine is [[Turing complete]] with only four general ''types'' of instructions: conditional GOTO, unconditional GOTO, assignment/replacement/substitution, and HALT.&lt;ref&gt;However, a few different assignment instructions (e.g. DECREMENT, INCREMENT and ZERO/CLEAR/EMPTY for a Minsky machine) are also required for Turing-completeness; their exact specification is somewhat up to the designer. The unconditional GOTO is a convenience; it can be constructed by initializing a dedicated location to zero e.g. the instruction &quot; Z ← 0 &quot;; thereafter the instruction IF Z=0 THEN GOTO xxx will be unconditional.&lt;/ref&gt;

'''Simulation of an algorithm: computer (computor) language''': Knuth advises the reader that &quot;the best way to learn an algorithm is to try it . . . immediately take pen and paper and work through an example&quot;.&lt;ref&gt;Knuth 1973:4&lt;/ref&gt; But what about a simulation or execution of the real thing? The programmer must translate the algorithm into a language that the simulator/computer/computor can ''effectively'' execute. Stone gives an example of this: when computing the roots of a quadratic equation the computor must know how to take a square root. If they don't then for the algorithm to be effective it must provide a set of rules for extracting a square root.&lt;ref&gt;Stone 1972:5. Methods for extracting roots are not trivial: see [[Methods of computing square roots]].&lt;/ref&gt;

This means that the programmer must know a &quot;language&quot; that is effective relative to the target computing agent (computer/computor).

But what model should be used for the simulation? Van Emde Boas observes &quot;even if we base [[Computational complexity theory|complexity theory]] on abstract instead of concrete machines, arbitrariness of the choice of a model remains. It is at this point that the notion of ''simulation'' enters&quot;.&lt;ref&gt;Cf page 875 in Peter van Emde Boas's &quot;Machine Models and Simulation&quot; in [[Jan van Leeuwen]] ed., 1990, &quot;Handbook of Theoretical Computer Science. Volume A: algorithms and Compexity&quot;, The MIT Press/Elsevier, ISBN 0-444-88071-2 (Volume A).&lt;/ref&gt; When speed is being measured, the instruction set matters. For example, the subprogram in Euclid's algorithm to compute the remainder would execute much faster if the programmer had a &quot;modulus&quot; (division) instruction available rather than just subtraction (or worse: just Minsky's &quot;decrement&quot;).

'''Structured programming, canonical structures''': Per the [[Church-Turing thesis]] any algorithm can be computed by a model known to be [[Turing complete]], and per Minsky's demonstrations Turing completeness requires only four instruction types—conditional GOTO, unconditional GOTO, assignment, HALT. Kemeny and Kurtz observe that while &quot;undisciplined&quot; use of unconditional GOTOs and conditional IF-THEN GOTOs can result in &quot;[[spaghetti code]]&quot; a programmer can write structured programs using these instructions; on the other hand &quot;it is also possible, and not too hard, to write badly structured programs in a structured language&quot;.&lt;ref&gt;[[John G. Kemeny]] and [[Thomas E. Kurtz]] 1985 ''Back to Basic: The History, Corruption, and Future of the Language'', Addison-Wesley Publishing Company, Inc. Reading, MA, ISBN 0-201-13433-0.&lt;/ref&gt; Tausworthe augments the three [[Structured program theorem|Böhm-Jacopini canonical structures]]:&lt;ref&gt;Tausworthe 1977:101&lt;/ref&gt; SEQUENCE, IF-THEN-ELSE, and WHILE-DO, with two more: DO-WHILE and CASE.&lt;ref&gt;Tausworthe 1977:142&lt;/ref&gt; An additional benefit of a structured program will be one that lends itself to [[proof of correctness|proofs of correctness]] using [[mathematical induction]].&lt;ref&gt;Knuth 1973 section 1.2.1, expanded by Tausworthe 1977 at pages 100ff and Chapter 9.1&lt;/ref&gt;

'''Canonical flowchart symbols&lt;ref&gt;cf Tausworthe 1977&lt;/ref&gt;''': The graphical aide called a [[flowchart]] offers a way to describe and document an algorithm (and a computer program of one). Like program flow of a Minsky machine, a flowchart always starts at the top of a page and proceeds down. Its primary symbols are only 4: the directed arrow showing program flow, the rectangle (SEQUENCE, GOTO), the diamond (IF-THEN-ELSE), and the dot (OR-tie). The Böhm-Jacopini canonical structures are made of these primitive shapes. Sub-structures can &quot;nest&quot; in rectangles but only if a single exit occurs from the superstructure. The symbols and their use to build the canonical structures are shown in the diagram.

== Examples ==
{{Further2|[[Algorithm examples]]}}

=== Algorithm example ===
  File:Sorting quicksort anim.gif|thumb|right|An animation of the [[quicksort|quicksort algorithm]] sorting an array of randomized values. The red bars mark the pivot element; at the start of the animation, the element farthest to the right hand side is chosen as the pivot.]]

One of the simplest algorithms is to find the largest number in an (unsorted) list of numbers. The solution necessarily requires looking at every number in the list, but only once at each. From this follows a simple algorithm, which can be stated in a high-level description English prose, as:

'''High-level description:'''
# Assume the first item is largest.
# Look at each of the remaining items in the list and if it is larger than the largest item so far, make a note of it.
# The last noted item is the largest in the list when the process is complete.

'''(Quasi-)formal description:'''
Written in prose but much closer to the high-level language of a computer program, the following is the more formal coding of the algorithm in [[pseudocode]] or [[pidgin code]]:

{{algorithm-begin|name=LargestNumber}}
   Input: A non-empty list of numbers ''L''.
   Output: The ''largest'' number in the list ''L''.

   ''largest'' ← ''L''&lt;sub&gt;0&lt;/sub&gt;
   '''for each''' ''item'' '''in''' the list ''(Length(L)≥1)'', '''do'''
     '''if''' the ''item'' &gt; ''largest'', '''then'''
       ''largest'' ← the ''item''
   '''return''' ''largest''
{{algorithm-end}}

=== Euclid’s algorithm ===

  File:Euclid's algorithm Book VII Proposition 2 2.png|250px|thumb|right|The example-diagram of Euclid's algorithm from T.L. Heath 1908 with more detail added. Euclid does not go beyond a third measuring and gives no numerical examples. Nicomachus gives the example of 49 and 21: &quot;I subtract the less from the greater; 28 is left; then again I subtract from this the same 21 (for this is possible); 7 is left; I subtact this from 21, 14 is left; from which I again subtract 7 (for this is possible); 7 will be left, but 7 cannot be subtracted from 7.&quot; Heath comments that &quot;The last phrase is curious, but the meaning of it is obvious enough, as also the meaning of the phrase about ending &quot;at one and the same number&quot;(Heath 1908:300).]]

[[Euclid]]’s algorithm appears as Proposition II in Book VII  &quot;Elementary Number Theory&quot;) of his ''[[Euclid's Elements|Elements]]''.&lt;ref&gt;Heath 1908:300; Hawking’s Dover 2005 edition derives from Heath.&lt;/ref&gt;  Euclid poses the problem: &quot;Given two numbers not prime to one another, to find their greatest common measure&quot;. He defines &quot;A number [to be] a multitude composed of units&quot;: a counting number, a positive integer not including 0. And to &quot;measure&quot; is to place a shorter measuring length ''s'' successively (''q'' times) along longer length ''l'' until the remaining portion ''r'' is less than the shorter length ''s''.&lt;ref&gt;&quot; 'Let CD, measuring BF, leave FA less than itself.' This is a neat abbreviation for saying, measure along BA successive lengths equal to CD until a point F is reached such that the length FA remaining is less than CD; in other words, let BF be the largest exact multiple of CD contained in BA&quot; (Heath 1908:297&lt;/ref&gt; In modern words, remainder ''r = l − q*s'', ''q'' being the quotient, or remainder ''r'' is the &quot;modulus&quot;, the integer-fractional part left over after the division.&lt;ref&gt;For modern treatments using division in the algorithm see Hardy and Wright 1979:180, Knuth 1973:2 (Volume 1), plus more discussion of Euclid's algorithm in Knuth 1969:293-297 (Volume 2).&lt;/ref&gt;

For Euclid’s method to succeed, the starting lengths must satisfy two requirements: (i) the lengths must not be 0, AND (ii) the subtraction must be “proper”, a test must guarantee that the smaller of the two numbers is subtracted from the larger (alternately, the two can be equal so their subtraction yields 0).

Euclid's original proof adds a third: the two lengths are not prime to one another. Euclid stipulated this so that he could construct a [[reductio ad absurdum]] proof that the two numbers' common measure is in fact the ''greatest''.&lt;ref&gt;Euclid covers this question in his Proposition 1.&lt;/ref&gt; While Nicomachus' algorithm is the same as Euclid's, when the numbers are prime to one another it yields the number &quot;1&quot; for their common measure. So to be precise the following is really Nicomachus' algorithm.

==== Example ====
[[File:Euclids-algorithm-example-1599-650.gif|350px|thumb|right|A graphical expression on Euclid's algorithm using example with 1599 and 650.]]

Example of 1599 and 650:
{| class=&quot;wikitable&quot;
|-
| Step 1 || 1599 = 650*2 + 299
|-
| Step 2 || 650 = 299*2 + 52
|-
| Step 3 || 299 = 52*5 + 39
|-
| Step 4 || 52 = 39*1 + 13
|-
| Step 5 || 39 = 13*3 + 0
|}

==== Computer language for Euclid's algorithm ====
Only a few instruction ''types'' are required to execute Euclid's algorithm—some logical tests (conditional GOTO), unconditional GOTO, assignment (replacement), and subtraction.
* A ''location'' is symbolized by upper case letter(s), e.g. S, A, etc.
* The varying quantity (number) in a location will be written in lower case letter(s) and (usually) associated with the location's name. For example, location L at the start might contain the number ''l'' = 3009.

==== An inelegant program for Euclid's algorithm ====
  File:Euclid's algorithm Inelegant program 1.png|thumb|right|&quot;Inelegant&quot; is a translation of Knuth's version of the algorithm with a subtraction-based remainder-loop replacing his use of division (or a &quot;modulus&quot; instruction). Derived from Knuth 1973:2–4. Depending on the two numbers &quot;Inelegant&quot; may compute the g.c.d. in fewer steps than &quot;Elegant&quot;.]]

The following algorithm is framed as Knuth's 4-step version of Euclid's and Nichomachus', but rather than using division to find the remainder it uses successive subtractions of the shorter length ''s'' from the remaining length ''r'' until ''r'' is less than ''s''. The high-level description, shown in boldface, is adapted from Knuth 1973:2–4:

'''INPUT''':
: 1 [Into two locations L and S put the numbers ''l'' and ''s'' that represent the two lengths]: INPUT L, S
: 2 [Initialize R: make the remaining length ''r'' equal to the starting/initial/input length ''l''] R ← L

'''E0: [Ensure ''r'' ≥ ''s''.]'''
: 3 [Insure the smaller of the two numbers is in S and the larger in R]: IF R &gt; S THEN the contents of L is the larger number so skip over the exchange-steps 4, 5 and 6: GOTO step 6 ELSE swap the contents of R and S.]
: 4 L ← R (this first step is redundant, but will be useful for later discussion).
: 5 R ← S
: 6 S ← L

''' E1:[Find remainder]''': Until the remaining length ''r'' in R is less than the shorter length ''s'' in S, repeatedly subtract the measuring number ''s'' in S from the remaining length ''r'' in R.
: 7 IF S &gt; R THEN done measuring so GOTO 10 ELSE measure again,
: 8 R ← R − S
: 9 [Remainder-loop]: GOTO 7.

'''E2: [Is the remainder 0?]''': EITHER (i) the last measure was exact and the remainder in R is 0 program can halt, OR (ii) the algorithm must continue: the last measure left a remainder in R less than measuring number in S.
: 10 IF R = 0 then done so GOTO step 15 ELSE continue to step 11,

'''E3: [Interchange ''s'' and ''r'' ]''': The nut of Euclid's algorithm. Use remainder ''r'' to measure what was previously smaller number ''s'':; L serves as a temporary location.
: 11 L ← R
: 12 R ← S
: 13 S ← L
: 14 [Repeat the measuring process]: GOTO 7

'''OUTPUT''':

: 15 [Done. S contains the greatest common divisor]: PRINT S

'''DONE''':
: 16  HALT, END, STOP.

==== An elegant program for Euclid's algorithm ====
The following version of Euclid's algorithm requires only 6 core instructions to do what 13 are required to do by &quot;Inelegant&quot;; worse, &quot;Inelegant&quot; requires more ''types'' of instructions. The flowchart of &quot;Elegant&quot; can be found at the top of this article. In the (unstructured) Basic language the steps are numbered, and the instruction LET [ ] = [ ] is the assignment instruction symbolized by ←.
&lt;pre&gt;
   5 REM Euclid's algorithm for greatest common divisor
   6 PRINT &quot;Type two integers greater than 0&quot;
   10 INPUT A,B
   20 IF B=0 THEN GOTO 80
   30 IF A &gt; B THEN GOTO 60
   40 LET B=B-A
   50 GOTO 20
   60 LET A=A-B
   70 GOTO 20
   80 PRINT A
   90 END
&lt;/pre&gt;
'''How &quot;Elegant&quot; works''': In place of an outer &quot;Euclid loop&quot;, &quot;Elegant&quot; shifts back and forth between two &quot;co-loops&quot;, an A &gt; B loop that computes A ← A − B, and a B ≤ A loop that computes B ← B − A. This works because, when at last the minuend M is less than or equal to the subtrahend S ( Difference = Minuend − Subtrahend), the minuend can become ''s'' (the new measuring length) and the subtrahend can become the new ''r'' (the length to be measured); in other words the &quot;sense&quot; of the subtraction reverses.

=== Testing the Euclid algorithms ===
Does an algorithm do what its author wants it to do? A few test cases usually suffice to confirm core functionality. One source&lt;ref&gt;{{cite web|url=http://aleph0.clarku.edu/~djoyce/java/elements/bookVII/propVII2.html |title=Euclid's Elements, Book VII, Proposition 2 |publisher=Aleph0.clarku.edu |date= |accessdate=2012-05-20}}&lt;/ref&gt; uses 3009 and 884. Knuth suggested 40902, 24140. Another interesting case is the two [[relatively prime]] numbers 14157 and 5950.

But exceptional cases must be identified and tested. Will &quot;Inelegant&quot; perform properly when R &gt; S, S &gt; R, R = S? Ditto for &quot;Elegant&quot;: B &gt; A, A &gt; B, A = B? (Yes to all). What happens when one number is zero, both numbers are zero? (&quot;Inelegant&quot; computes forever in all cases; &quot;Elegant&quot; computes forever when A = 0.) What happens if ''negative'' numbers are entered? Fractional numbers? If the input numbers, i.e. the [[domain (mathematics)|domain]] of the function computed by the algorithm/program, is to include only positive integers including zero, then the failures at zero indicate that the algorithm (and the program that [[instance (computer science)|instantiates]] it) is a [[partial function]] rather than a [[total function]]. A notable failure due to exceptions is the [[Ariane V]] rocket failure.

'''Proof of program correctness by use of mathematical induction''': Knuth demonstrates the application of [[mathematical induction]] to an &quot;extended&quot; version of Euclid's algorithm, and he proposes &quot;a general method applicable to proving the validity of any algorithm&quot;.&lt;ref&gt;Knuth 1973:13–18. He credits &quot;the formulation of algorithm-proving in terms of asertions and induction&quot; to R. W. Floyd, Peter Naur, C. A. R. Hoare, H. H. Goldstine and J. von Neumann. Tausworth 1977 borrows Knuth's Euclid example and extends Knuth's method in section 9.1 ''Formal Proofs'' (pages 288–298).&lt;/ref&gt; Tausworthe proposes that a measure of the complexity of a program be the length of its correctness proof.&lt;ref&gt;Tausworthe 1997:294&lt;/ref&gt;

=== Measuring and improving the Euclid algorithms ===
'''Elegance (compactness) versus goodness (speed) ''': With only 6 core instructions, &quot;Elegant&quot; is the clear winner compared to &quot;Inelegant&quot; at 13 instructions. However, &quot;Inelegant&quot; is ''faster'' (it arrives at HALT in fewer steps). [[Algorithm analysis]]&lt;ref&gt;cf Knuth 1973:7 (Vol. I), and his more-detailed analyses on pp. 1969:294-313 (Vol II).&lt;/ref&gt; indicates why this is the case: &quot;Elegant&quot; does ''two'' conditional tests in every subtraction loop, whereas &quot;Inelegant&quot; only does one. As the algorithm (usually) requires many loop-throughs, ''on average'' much time is wasted doing a &quot;B = 0?&quot; test that is needed only after the remainder is computed.

'''Can the algorithms be improved?''': Once the programmer judges a program &quot;fit&quot; and &quot;effective&quot;—that is, it computes the function intended by its author—then the question becomes, can it be improved?

The compactness of &quot;Inelegant&quot; can be improved by the elimination of 5 steps. But Chaitin proved that compacting an algorithm cannot be automated by a generalized algorithm;&lt;ref&gt;Breakdown occurs when an algorithm tries to compact itself. Success would solve the [[Halting problem]].&lt;/ref&gt; rather, it can only be done [[heuristic]]ally, i.e. by exhaustive search (examples to be found at [[Busy beaver]]), trial and error, cleverness, insight, application of [[inductive reasoning]], etc. Observe that steps 4, 5 and 6 are repeated in steps 11, 12 and 13. Comparison with &quot;Elegant&quot; provides a hint that these steps together with steps 2 and 3 can be eliminated. This reduces the number of core instructions from 13 to 8, which makes it &quot;more elegant&quot; than &quot;Elegant&quot; at 9 steps.

The speed of &quot;Elegant&quot; can be improved by moving the B=0? test outside of the two subtraction loops. This change calls for the addition of 3 instructions (B=0?, A=0?, GOTO). Now &quot;Elegant&quot; computes the example-numbers faster; whether for any given A, B and R, S this is always the case would require a detailed analysis.

== Algorithmic analysis ==
{{Main|Analysis of algorithms}}
It is frequently important to know how much of a particular resource (such as time or storage) is theoretically required for a given algorithm. Methods have been developed for the [[analysis of algorithms]] to obtain such quantitative answers (estimates); for example, the sorting algorithm above has a time requirement of O(''n''), using the [[big O notation]] with ''n'' as the length of the list. At all times the algorithm only needs to remember two values: the largest number found so far, and its current position in the input list. Therefore it is said to have a space requirement of ''O(1)'', if the space required to store the input numbers is not counted, or O(''n'') if it is counted.

Different algorithms may  complete the same task with a different set of instructions in less or more time, space, or '[[algorithmic efficiency|effort]]' than others. For example, a [[binary search]] algorithm will usually outperform a [[Brute-force search|brute force]] sequential search when used for [[lookup table|table lookup]]s on sorted lists.

=== Formal versus empirical ===
{{Main|Empirical algorithmics|Profiling (computer programming)|Program optimization}}
The [[analysis of algorithms|analysis and study of algorithms]] is a discipline of [[computer science]], and is often practiced abstractly without the use of a specific [[programming language]] or implementation. In this sense, algorithm analysis resembles other mathematical disciplines in that it focuses on the underlying properties of the algorithm and not on the specifics of any particular implementation. Usually [[pseudocode]] is used for analysis as it is the simplest and most general representation. However, ultimately, most algorithms are usually implemented on particular hardware / software platforms and their [[algorithmic efficiency]] is eventually put to the test using real code. For the solution of a &quot;one off&quot; problem, the efficiency of a particular algorithm may not have significant consequences (unless n is extremly large) but for algorithms designed for fast interactive, commercial or long life scientific usage it may be critical. Scaling from small n to large n frequently exposes inefficient algorithms that are otherwise benign.

Empirical testing is useful because it may  uncover unexpected interactions that affect performance. [[Benchmark (computing)|Benchmark]]s may  be used to compare before/after potential improvements to an algorithm after program optimization.

==== FFT speedup ====
{{Main|Algorithmic efficiency}}
To illustrate the potential improvements possible even in some extremely &quot;well established&quot; algorithms, a recent significant innovation, relating to [[FFT]] algorithms (used very heavily in the field of image processing), may have decreased processing times by a factor as high as 10,000 . The impact of this speedup enables, for example, portable computing devices (as well as other devices) to consume less power&lt;ref name=Hassanieh12&gt;Haitham Hassanieh, Piotr Indyk, Dina Katabi, and Eric Price, &quot;[http://groups.csail.mit.edu/netmit/sFFT/paper.pdf Simple and Practical Algorithm for Sparse Fourier Transform],&quot; ACM-SIAM Symposium On Discrete Algorithms (SODA), Kyoto, January 2012.  See also the [http://groups.csail.mit.edu/netmit/sFFT/ sFFT Web Page].&lt;/ref&gt;

== Classification ==
There are various ways to classify algorithms, each with its own merits.

=== By implementation ===
One way to classify algorithms is by implementation means.

* '''Recursion''' or '''iteration''': A [[recursive algorithm]] is one that invokes (makes reference to) itself repeatedly until a certain condition matches, which is a method common to [[functional programming]]. [[Iteration|Iterative]] algorithms use repetitive constructs like [[Program loops|loops]] and sometimes additional data structures like [[Stack (data structure)|stacks]] to solve the given problems. Some problems are naturally suited for one implementation or the other. For example, [[towers of Hanoi]] is a well understood in recursive implementation. Every recursive version has an equivalent (but possibly more or less complex) iterative version, and vice versa.
* '''Logical''': An algorithm may  be viewed as controlled [[Deductive reasoning|logical deduction]]. This notion may  be expressed as: '''Algorithm = logic + control'''.&lt;ref&gt;Kowalski 1979&lt;/ref&gt; The logic component expresses the axioms that may  be used in the computation and the control component determines the way in which deduction is applied to the axioms. This is the basis for the [[logic programming]] paradigm. In pure logic programming languages the control component is fixed and algorithms are specified by supplying only the logic component. The appeal of this approach is the elegant [[Formal semantics of programming languages|semantics]]: a change in the axioms has a well-defined change in the algorithm.
* '''Serial''' or '''parallel''' or '''distributed''': Algorithms are usually discussed with the assumption that computers execute one instruction of an algorithm at a time. Those computers are sometimes called serial computers. An [[algorithm design]]ed for such an environment is called a serial algorithm, as opposed to [[parallel algorithm]]s or [[distributed algorithms]]. Parallel algorithms take advantage of computer architectures where several processors can work on a problem at the same time, whereas distributed algorithms utilize multiple machines connected with a [[Computer Network|network]]. Parallel or distributed algorithms divide the problem into more symmetrical or asymmetrical subproblems and collect the results back together. The resource consumption in such algorithms is not only processor cycles on each processor but also the communication overhead between the processors. Sorting algorithms can be parallelized efficiently, but their communication overhead is expensive. Iterative algorithms are generally parallelizable. Some problems have no parallel algorithms, and are called inherently serial problems.
* '''Deterministic''' or '''non-deterministic''': [[Deterministic algorithm]]s solve the problem with exact decision at every step of the algorithm whereas [[non-deterministic algorithm]]s solve problems via guessing although typical guesses are made more accurate through the use of [[heuristics]].
* '''Exact''' or '''approximate''': While many algorithms reach an exact solution, [[approximation algorithm]]s seek an approximation that is close to the true solution. Approximation may  use either a deterministic or a random strategy. Such algorithms have practical value for many hard problems.
* '''[[Quantum algorithm]]''' run on a realistic model of [[quantum computation]]. The term is usually used for those algorithms which seem inherently quantum, or use some essential feature of quantum computation such as [[quantum superposition]] or [[quantum entanglement]].

=== By design paradigm ===
Another way of classifying algorithms is by their design methodology or paradigm. There is a certain number of paradigms, each different from the other. Furthermore, each of these categories will include many different types of algorithms. Some commonly found paradigms include:

* '''[[Brute force search|Brute-force]]''' or '''exhaustive search'''.  This is the naive method of trying every possible solution to see which is best.&lt;ref&gt;{{cite book |title=Fundamental Concepts for the Software Quality Engineer |author=Sue Carroll, Taz Daughtrey |pages=282 et seq.|url=http://books.google.com/?id=bz_cl3B05IcC&amp;pg=PA282 |isbn=978-0-87389-720-4 |date=2007-07-04}}&lt;/ref&gt;
* '''Divide and conquer'''. A [[divide and conquer algorithm]] repeatedly reduces an instance of a problem to one or more smaller instances of the same problem (usually [[recursion|recursively]]) until the instances are small enough to solve easily. One such example of divide and conquer is [[mergesort|merge sorting]]. Sorting can be done on each segment of data after dividing data into segments and sorting of entire data can be obtained in the conquer phase by merging the segments. A simpler variant of divide and conquer is called a '''decrease and conquer algorithm''', that solves an identical subproblem and uses the solution of this subproblem to solve the bigger problem. Divide and conquer divides the problem into multiple subproblems and so the conquer stage will be more complex than decrease and conquer algorithms. An example of decrease and conquer algorithm is the [[binary search algorithm]].
* '''[[Dynamic programming]]'''. When a problem shows [[optimal substructure]], meaning the optimal solution to a problem can be constructed from optimal solutions to subproblems, and [[overlapping subproblems]], meaning the same subproblems are used to solve many different problem instances, a quicker approach called ''dynamic programming'' avoids recomputing solutions that have already been computed. For example, [[Floyd–Warshall algorithm]], the shortest path to a goal from a vertex in a weighted [[graph (mathematics)|graph]] can be found by using the shortest path to the goal from all adjacent vertices. Dynamic programming and [[memoization]] go together. The main difference between dynamic programming and divide and conquer is that subproblems are more or less independent in divide and conquer, whereas subproblems overlap in dynamic programming. The difference between dynamic programming and straightforward recursion is in caching or memoization of recursive calls. When subproblems are independent and there is no repetition, memoization does not help; hence dynamic programming is not a solution for all complex problems. By using memoization or maintaining a [[Mathematical table|table]] of subproblems already solved, dynamic programming reduces the exponential nature of many problems to polynomial complexity.
* '''The greedy method'''. A [[greedy algorithm]] is similar to a [[dynamic programming|dynamic programming algorithm]], but the difference is that solutions to the subproblems do not have to be known at each stage; instead a &quot;greedy&quot; choice can be made of what looks best for the moment. The greedy method extends the solution with the best possible decision (not all feasible decisions) at an algorithmic stage based on the current local optimum and the best decision (not all possible decisions) made in a previous stage. It is not exhaustive, and does not give an accurate answer to many problems. But when it works, it will be the fastest method. The most popular greedy algorithm is finding the minimal spanning tree as given by [[Huffman coding|Huffman Tree]], [[kruskal's algorithm|Kruskal]], [[Prim's algorithm|Prim]], [[Sollin's algorithm|Sollin]].
* '''Linear programming'''. When solving a problem using [[linear programming]], specific [[inequality (mathematics)|inequalities]] involving the inputs are found and then an attempt is made to maximize (or minimize) some linear function of the inputs. Many problems (such as the [[Maximum flow problem|maximum flow]] for directed graphs) can be stated in a linear programming way, and then be solved by a 'generic' algorithm such as the [[simplex algorithm]]. A more complex variant of linear programming is called integer programming, where the solution space is restricted to the [[integers]].
* '''[[Reduction (complexity)|Reduction]]'''. This technique involves solving a difficult problem by transforming it into a better known problem for which we have (hopefully) [[asymptotically optimal]] algorithms. The goal is to find a reducing algorithm whose [[Computational complexity theory|complexity]] is not dominated by the resulting reduced algorithm's. For example, one [[selection algorithm]] for finding the median in an unsorted list involves first sorting the list (the expensive portion) and then pulling out the middle element in the sorted list (the cheap portion).  This technique is also known as ''transform and conquer''.
* '''Search and enumeration'''. Many problems (such as playing [[chess]]) can be modeled as problems on [[graph theory|graphs]]. A [[graph exploration algorithm]] specifies rules for moving around a graph and is useful for such problems. This category also includes [[search algorithm]]s, [[branch and bound]] enumeration and [[backtracking]].
# [[Randomized algorithm]]s are those that make some choices randomly (or pseudo-randomly); for some problems, it can in fact be proven that the fastest solutions must involve some [[randomness]]. There are two large classes of such algorithms:
## [[Monte Carlo algorithm]]s return a correct answer with high-probability. E.g. [[RP (complexity)|RP]] is the subclass of these that run in [[polynomial time]])
## [[Las Vegas algorithm]]s always return the correct answer, but their running time is only probabilistically bound, e.g. [[Zero-error Probabilistic Polynomial time|ZPP]].
# In [[optimization problem]]s, heuristic algorithms do not try to find an optimal solution, but an approximate solution where the time or resources are limited. They are not practical to find perfect solutions. An example of this would be [[local search (optimization)|local search]], [[tabu search]], or [[simulated annealing]] algorithms, a class of heuristic probabilistic algorithms that vary the solution of a problem by a random amount. The name &quot;simulated annealing&quot; alludes to the metallurgic term meaning the heating and cooling of metal to achieve freedom from defects. The purpose of the random variance is to find close to globally optimal solutions rather than simply locally optimal ones, the idea being that the random element will be decreased as the algorithm settles down to a solution. [[Approximation algorithms]] are those heuristic algorithms that additionally provide some bounds on the error. [[Genetic algorithm]]s attempt to find solutions to problems by mimicking biological [[evolution]]ary processes, with a cycle of random mutations yielding successive generations of &quot;solutions&quot;. Thus, they emulate reproduction and &quot;survival of the fittest&quot;. In [[genetic programming]], this approach is extended to algorithms, by regarding the algorithm itself as a &quot;solution&quot; to a problem.

=== By field of study ===
{{See also|List of algorithms}}
Every field of science has its own problems and needs efficient algorithms. Related problems in one field are often studied together. Some example classes are [[search algorithm]]s, [[sorting algorithm]]s, [[merge algorithm]]s, [[numerical analysis|numerical algorithms]], [[graph theory|graph algorithms]], [[string algorithms]], [[computational geometry|computational geometric algorithms]], [[combinatorial|combinatorial algorithms]], [[medical algorithm]]s, [[machine learning]], [[cryptography]], [[data compression]] algorithms and [[parsing|parsing techniques]].

Fields tend to overlap with each other, and algorithm advances in one field may  improve those of other, sometimes completely unrelated, fields. For example, dynamic programming was invented for optimization of resource consumption in industry, but is now used in solving a broad range of problems in many fields.

=== By complexity ===
{{See also|Complexity class| Parameterized complexity}}
Algorithms can be classified by the amount of time they need to complete compared to their input size. There is a wide variety: some algorithms complete in linear time relative to input size, some do so in an exponential amount of time or even worse, and some never halt. Additionally, some problems may  have multiple algorithms of differing complexity, while other problems might have no algorithms or no known efficient algorithms. There are also mappings from some problems to other problems. Owing to this, it was found to be more suitable to classify the problems themselves instead of the algorithms into equivalence classes based on the complexity of the best possible algorithms for them.

Burgin (2005, p.&amp;nbsp;24) uses a generalized definition of algorithms that relaxes the common requirement that the output of the algorithm that computes a function must be determined after a finite number of steps.  He defines a super-recursive class of algorithms as &quot;a class of algorithms in which it is possible to compute functions not computable by any Turing machine&quot; (Burgin 2005, p.&amp;nbsp;107). This is closely related to the study of methods of [[hypercomputation]].

== Continuous algorithms ==
The adjective &quot;continuous&quot; when applied to the word &quot;algorithm&quot; can mean:
# An algorithm operating on data that represents continuous quantities, even though this data is represented by discrete approximations—such algorithms are studied in [[numerical analysis]]; or
# An algorithm in the form of a [[differential equation]] that operates continuously on the data, running on an [[analog computer]].&lt;ref&gt;[http://books.google.com/books?id=sgDHJlafMskC Adaptation and learning in automatic systems], page 54, Ya. Z. Tsypkin, Z. J. Nikolic, Academic Press, 1971, ISBN 978-0-12-702050-1&lt;/ref&gt;

== Legal issues ==
:''See also: [[Software patents]] for a general overview of the patentability of software, including computer-implemented algorithms.''

Algorithms, by themselves, are not usually patentable. In the United States, a claim consisting solely of simple manipulations of abstract concepts, numbers, or signals does not constitute &quot;processes&quot; (USPTO 2006), and hence algorithms are not patentable (as in [[Gottschalk v. Benson]]). However, practical applications of algorithms are sometimes patentable. For example, in [[Diamond v. Diehr]], the application of a simple [[feedback]] algorithm to aid in the curing of [[synthetic rubber]] was deemed patentable. The [[Software patent debate|patenting of software]] is highly controversial, and there are highly criticized patents involving algorithms, especially [[data compression]] algorithms, such as [[Unisys]]' [[Graphics Interchange Format#Unisys and LZW patent enforcement|LZW patent]].

Additionally, some cryptographic algorithms have export restrictions (see [[export of cryptography]]).

== Etymology ==
The word ''&quot;Algorithm&quot;'', or ''&quot;[[Algorism]]&quot;'' in some other writing versions, comes from the name [[al-Khwārizmī]], pronounced in classical Arabic as Al-Khwarithmi. [[Al-Khwārizmī]] ([[Persian language|Persian]] ''خوارزمي'', c. 780-850) was a [[Persian people|Persian]] [[mathematician]], [[astronomer]], [[geographer]] and a [[scholar]] in the [[House of Wisdom]] in [[Baghdad]], whose name means ''&quot;the native of [[Khwarezm]]&quot;'', a city that was part of the [[Greater Iran]] during his era and now is in modern day [[Uzbekistan]]{{sfn|Toomer|1990|loc={{citation not found}}}}&lt;ref name=&quot;Hogendijk&quot;&gt;{{cite journal|first=Jan P.|last=Hogendijk|title=al-Khwarzimi|journal=Pythagoras|volume=38|issue=2|year=1998|pages=4–5|url=http://www.kennislink.nl/web/show?id=116543|ref=harv}} {{Dead link|date=March 2010}}&lt;/ref&gt;&lt;ref name=&quot;Oaks&quot;&gt;{{cite web|first=Jeffrey A.|last= Oaks|url=http://facstaff.uindy.edu/~oaks/MHMC.htm|title=Was al-Khwarizmi an applied algebraist?|publisher=[[University of Indianapolis]]|accessdate=2008-05-30}}&lt;/ref&gt; He wrote a treatise in the Arabic language during the 9th century, which was translated into [[Latin]] in the 12th century under the title ''Algoritmi de numero Indorum''. This title means &quot;Algoritmi on the numbers of the Indians&quot;, where &quot;Algoritmi&quot; was the translator's Latinization of Al-Khwarizmi's name.&lt;ref&gt;[http://books.google.co.uk/books?id=3Sfrxde0CXIC&amp;printsec=frontcover&amp;source=gbs_ge_summary_r&amp;cad=0#v=onepage&amp;q&amp;f=false Al-Khwarizmi: The Inventor of Algebra], by Corona Brezina (2006)&lt;/ref&gt; Al-Khwarizmi was the most widely read mathematician in Europe in the late Middle Ages, primarily through his other book, the [[Al-Jabr|Algebra]].&lt;ref&gt;[http://www-history.mcs.st-and.ac.uk/Extras/Boyer_Foremost_Text.html Foremost mathematical texts in history], according to [[Carl B. Boyer]].&lt;/ref&gt; In late medieval Latin, ''algorismus'', the corruption of his name, simply meant the &quot;decimal number system&quot; that is still the meaning of modern English [[algorism]]. In 17th century French the word's form, but not its meaning, changed to ''algorithme''. English adopted the French very soon afterwards, but it wasn't until the late 19th century that &quot;Algorithm&quot; took on the meaning that it has in modern English.&lt;ref&gt;Etymology of algorithm at [http://dictionary.reference.com/browse/algorithm Dictionary.Reference.com]&lt;/ref&gt;

== History: Development of the notion of &quot;algorithm&quot; ==

=== Origin ===
The word algorithm comes from the name of the 9th century Persian Muslim mathematician Abu Abdullah Muhammad ibn Musa Al-Khwarizmi. The word algorism originally referred only to the rules of performing arithmetic using Hindu-Arabic numerals but evolved via European Latin translation of Al-Khwarizmi's name into algorithm by the 18th century. The use of the word evolved to include all definite procedures for solving problems or performing tasks. &lt;ref&gt;http://www.scriptol.com/programming/algorithm-history.php&lt;/ref&gt;

=== Discrete and distinguishable symbols ===
'''Tally-marks''': To keep track of their flocks, their sacks of grain and their money the ancients used tallying: accumulating stones or marks scratched on sticks, or making discrete symbols in clay. Through the Babylonian and Egyptian use of marks and symbols, eventually [[Roman numerals]] and the [[abacus]] evolved  Dilson, p.&amp;nbsp;16–41). Tally marks appear prominently in [[unary numeral system]] arithmetic used in [[Turing machine]] and [[Post–Turing machine]] computations.

=== Manipulation of symbols as &quot;place holders&quot; for numbers: algebra ===
The work of the ancient [[Greek mathematics|Greek geometers]] ([[Euclidean algorithm]]), [[Islamic mathematics|Persian mathematician]] [[Muhammad ibn Mūsā al-Khwārizmī|Al-Khwarizmi]]  from whose name the terms &quot;[[algorism]]&quot; and &quot;algorithm&quot; are derived), and Western European mathematicians culminated in [[Gottfried Leibniz|Leibniz]]'s notion of the [[calculus ratiocinator]] (ca 1680):
  quote|A good century and a half ahead of his time, Leibniz proposed an algebra of logic, an algebra that would specify the rules for manipulating logical concepts in the manner that ordinary algebra specifies the rules for manipulating numbers.&lt;ref&gt;Davis 2000:18&lt;/ref&gt;}}

=== Mechanical contrivances with discrete states ===
'''The clock''': Bolter credits the invention of the weight-driven [[clock]] as &quot;The key invention [of Europe in the Middle Ages]&quot;, in particular the [[verge escapement]]&lt;ref&gt;Bolter 1984:24&lt;/ref&gt; that provides us with the tick and tock of a mechanical clock. &quot;The accurate automatic machine&quot;&lt;ref&gt;Bolter 1984:26&lt;/ref&gt; led immediately to &quot;mechanical [[automata theory|automata]]&quot; beginning in the 13th century and finally to &quot;computational machines&quot;—the [[difference engine]] and [[analytical engine]]s of [[Charles Babbage]] and Countess [[Ada Lovelace]], mid-19th century.&lt;ref&gt;Bolter 1984:33–34, 204–206)&lt;/ref&gt;  Lovelace is credited with the first creation of an algorithm intended for processing on a computer - Babbage's analytical engine, the first device considered a real [[Turing-complete]] [[computer]] instead of just a [[calculator]] - and is sometimes called &quot;history's first programmer&quot; as a result, though a full implementation of Babbage's second device would not be realized until decades after her lifetime.

'''Logical machines 1870—[[Stanley Jevons]]' &quot;logical abacus&quot; and &quot;logical machine&quot;''': The technical problem was to reduce [[Boolean equation]]s when presented in a form similar to what are now known as [[Karnaugh map]]s. Jevons (1880) describes first a simple &quot;abacus&quot; of &quot;slips of wood furnished with pins, contrived so that any part or class of the [logical] combinations can be picked out mechanically . . . More recently however I have reduced the system to a completely mechanical form, and have thus embodied the whole of the indirect process of inference in what may  be called a '''Logical Machine'''&quot; His machine came equipped with &quot;certain moveable wooden rods&quot; and &quot;at the foot are 21 keys like those of a piano [etc] . . .&quot;. With this machine he could analyze a &quot;[[syllogism]] or any other simple logical argument&quot;.&lt;ref&gt;All quotes from W. Stanley Jevons 1880 ''Elementary Lessons in Logic: Deductive and Inductive'', Macmillan and Co., London and New York. Republished as a googlebook; cf Jevons 1880:199–201. Louis Couturat 1914 ''the Algebra of Logic'', The Open Court Publishing Company, Chicago and London. Republished as a googlebook; cf Couturat 1914:75–76 gives a few more details; interestingly he compares this to a typewriter as well as a piano. Jevons states that the account is to be found at Jan . 20, 1870 ''The Proceedings of the Royal Society''.&lt;/ref&gt;

This machine he displayed in 1870 before the Fellows of the Royal Society.&lt;ref&gt;Jevons 1880:199–200&lt;/ref&gt; Another logician [[John Venn]], however, in his 1881 ''Symbolic Logic'', turned a jaundiced eye to this effort: &quot;I have no high estimate myself of the interest or importance of what are sometimes called logical machines ... it does not seem to me that any contrivances at present known or likely to be discovered really deserve the name of logical machines&quot;; see more at [[Algorithm characterizations]]. But not to be outdone he too presented &quot;a plan somewhat analogous, I apprehend, to Prof. Jevon's ''abacus'' ... [And] [a]gain, corresponding to Prof. Jevons's logical machine, the following contrivance may  be described. I prefer to call it merely a logical-diagram machine ... but I suppose that it could do very completely all that can be rationally expected of any logical machine&quot;.&lt;ref&gt;All quotes from John Venn 1881 ''Symbolic Logic'', Macmillan and Co., London. Republished as a googlebook. cf Venn 1881:120–125. The interested reader can find a deeper explanation in those pages.&lt;/ref&gt;

'''Jacquard loom, Hollerith punch cards, telegraphy and telephony—the electromechanical relay''': Bell and Newell (1971) indicate that the [[Jacquard loom]] (1801), precursor to [[Hollerith cards]] (punch cards, 1887), and &quot;telephone switching technologies&quot; were the roots of a tree leading to the development of the first computers.&lt;ref&gt;Bell and Newell diagram 1971:39, cf. Davis 2000&lt;/ref&gt; By the mid-19th century the [[telegraph]], the precursor of the telephone, was in use throughout the world, its discrete and distinguishable encoding of letters as &quot;dots and dashes&quot; a common sound. By the late 19th century the [[ticker tape]] (ca 1870s) was in use, as was the use of Hollerith cards in the 1890 U.S. census. Then came the [[teleprinter]] (ca. 1910) with its punched-paper use of [[Baudot code]] on tape.

'''Telephone-switching networks''' of electromechanical [[relay]]s (invented 1835) was behind the work of [[George Stibitz]] (1937), the inventor of the digital adding device. As he worked in Bell Laboratories, he observed the &quot;burdensome' use of mechanical calculators with gears. &quot;He went home one evening in 1937 intending to test his idea... When the tinkering was over, Stibitz had constructed a binary adding device&quot;.&lt;ref&gt;* Melina Hill, Valley News Correspondent, ''A Tinkerer Gets a Place in History'', Valley News West Lebanon NH, Thursday March 31, 1983, page 13.&lt;/ref&gt;

Davis (2000) observes the particular importance of the electromechanical relay  with its two &quot;binary states&quot; ''open'' and ''closed''):
: It was only with the development, beginning in the 1930s, of electromechanical calculators using electrical relays, that machines were built having the scope Babbage had envisioned.&quot;&lt;ref&gt;Davis 2000:14&lt;/ref&gt;

=== Mathematics during the 19th century up to the mid-20th century ===
'''Symbols and rules''': In rapid succession the mathematics of [[George Boole]] (1847, 1854), [[Gottlob Frege]] (1879), and [[Giuseppe Peano]] (1888–1889) reduced arithmetic to a sequence of symbols manipulated by rules. Peano's ''The principles of arithmetic, presented by a new method'' (1888) was &quot;the first attempt at an axiomatization of mathematics in a symbolic language&quot;.&lt;ref&gt;van Heijenoort 1967:81ff&lt;/ref&gt;

But Heijenoort gives Frege (1879) this kudos: Frege's is &quot;perhaps the most important single work ever written in logic. ... in which we see a &quot; 'formula language', that is a ''lingua characterica'', a language written with special symbols, &quot;for pure thought&quot;, that is, free from rhetorical embellishments ... constructed from specific symbols that are manipulated according to definite rules&quot;.&lt;ref&gt;van Heijenoort's commentary on Frege's ''Begriffsschrift, a formula language, modeled upon that of arithmetic, for pure thought'' in van Heijenoort 1967:1&lt;/ref&gt; The work of Frege was further simplified and amplified by [[Alfred North Whitehead]] and [[Bertrand Russell]] in their [[Principia Mathematica]] (1910–1913).

'''The paradoxes''': At the same time a number of disturbing paradoxes appeared in the literature, in particular the [[Burali-Forti paradox]] (1897), the [[Russell paradox]] (1902–03), and the [[Richard Paradox]].&lt;ref&gt;Dixon 1906, cf. Kleene 1952:36–40&lt;/ref&gt; The resultant considerations led to [[Kurt Gödel]]'s paper (1931)—he specifically cites the paradox of the liar—that completely reduces rules of [[recursion]] to numbers.

'''Effective calculability''': In an effort to solve the [[Entscheidungsproblem]] defined precisely by Hilbert in 1928, mathematicians first set about to define what was meant by an &quot;effective method&quot; or &quot;effective calculation&quot; or &quot;effective calculability&quot; (i.e., a calculation that would succeed). In rapid succession the following appeared: [[Alonzo Church]], [[Stephen Kleene]] and [[J.B. Rosser]]'s [[λ-calculus]]&lt;ref&gt;cf. footnote in Alonzo Church 1936a in Davis 1965:90 and 1936b in Davis 1965:110&lt;/ref&gt; a finely honed definition of &quot;general recursion&quot; from the work of Gödel acting on suggestions of [[Jacques Herbrand]] (cf. Gödel's Princeton lectures of 1934) and subsequent simplifications by Kleene.&lt;ref&gt;Kleene 1935–6 in Davis 1965:237ff, Kleene 1943 in Davis 1965:255ff&lt;/ref&gt; Church's proof&lt;ref&gt;Church 1936 in Davis 1965:88ff&lt;/ref&gt; that the Entscheidungsproblem was unsolvable, [[Emil Post]]'s definition of effective calculability as a worker mindlessly following a list of instructions to move left or right through a sequence of rooms and while there either mark or erase a paper or observe the paper and make a yes-no decision about the next instruction.&lt;ref&gt;cf. &quot;Formulation I&quot;, Post 1936 in Davis 1965:289–290&lt;/ref&gt; Alan Turing's proof of that the Entscheidungsproblem was unsolvable by use of his &quot;a- [automatic-] machine&quot;&lt;ref&gt;Turing 1936–7 in Davis 1965:116ff&lt;/ref&gt;—in effect almost identical to Post's &quot;formulation&quot;, [[J. Barkley Rosser]]'s definition of &quot;effective method&quot; in terms of &quot;a machine&quot;.&lt;ref&gt;Rosser 1939 in Davis 1965:226&lt;/ref&gt; [[S. C. Kleene]]'s proposal of a precursor to &quot;[[Church thesis]]&quot; that he called &quot;Thesis I&quot;,&lt;ref&gt;Kleene 1943 in Davis 1965:273–274&lt;/ref&gt; and a few years later Kleene's renaming his Thesis &quot;Church's Thesis&quot;&lt;ref&gt;Kleene 1952:300, 317&lt;/ref&gt; and proposing &quot;Turing's Thesis&quot;.&lt;ref&gt;Kleene 1952:376&lt;/ref&gt;

=== Emil Post (1936) and Alan Turing (1936–37, 1939) ===
Here is a remarkable coincidence of two men not knowing each other but describing a process of men-as-computers working on computations—and they yield virtually identical definitions.

[[Emil Post]] (1936) described the actions of a &quot;computer&quot; (human being) as follows:
:&quot;...two concepts are involved: that of a ''symbol space'' in which the work leading from problem to answer is to be carried out, and a fixed unalterable ''set of directions''.

His symbol space would be
:&quot;a two way infinite sequence of spaces or boxes... The problem solver or worker is to move and work in this symbol space, being capable of being in, and operating in but one box at a time.... a box is to admit of but two possible conditions, i.e., being empty or unmarked, and having a single mark in it, say a vertical stroke.

:&quot;One box is to be singled out and called the starting point. ...a specific problem is to be given in symbolic form by a finite number of boxes [i.e., INPUT] being marked with a stroke. Likewise the answer [i.e., OUTPUT] is to be given in symbolic form by such a configuration of marked boxes....

:&quot;A set of directions applicable to a general problem sets up a deterministic process when applied to each specific problem. This process will terminate only when it comes to the direction of type (C ) [i.e., STOP]&quot;.&lt;ref&gt;Turing 1936–7 in Davis 1965:289–290&lt;/ref&gt; See more at [[Post–Turing machine]]
[[File:Alan Turing.jpg|thumb|200px|Alan Turing's statue at [[Bletchley Park]].]]
[[Alan Turing]]'s work&lt;ref&gt;Turing 1936 in Davis 1965, Turing 1939 in Davis 1965:160&lt;/ref&gt; preceded that of Stibitz (1937); it is unknown whether Stibitz knew of the work of Turing. Turing's biographer believed that Turing's use of a typewriter-like model derived from a youthful interest: &quot;Alan had dreamt of inventing typewriters as a boy; Mrs. Turing had a typewriter; and he could well have begun by asking himself what was meant by calling a typewriter 'mechanical'&quot;.&lt;ref&gt;Hodges, p. 96&lt;/ref&gt; Given the prevalence of Morse code and telegraphy, ticker tape machines, and teletypewriters we might conjecture that all were influences.

Turing—his model of computation is now called a [[Turing machine]]—begins, as did Post, with an analysis of a human computer that he whittles down to a simple set of basic motions and &quot;states of mind&quot;. But he continues a step further and creates a machine as a model of computation of numbers.&lt;ref&gt;Turing 1936–7:116)&lt;/ref&gt;

:&quot;Computing is normally done by writing certain symbols on paper. We may  suppose this paper is divided into squares like a child's arithmetic book....I assume then that the computation is carried out on one-dimensional paper, i.e., on a tape divided into squares. I shall also suppose that the number of symbols which may  be printed is finite....

:&quot;The behavior of the computer at any moment is determined by the symbols which he is observing, and his &quot;state of mind&quot; at that moment. We may  suppose that there is a bound B to the number of symbols or squares which the computer can observe at one moment. If he wishes to observe more, he must use successive observations. We will also suppose that the number of states of mind which need be taken into account is finite...

:&quot;Let us imagine that the operations performed by the computer to be split up into 'simple operations' which are so elementary that it is not easy to imagine them further divided&quot;.&lt;ref name=&quot;Turing 1936-7 in Davis 1965:136&quot;&gt;Turing 1936–7 in Davis 1965:136&lt;/ref&gt;

Turing's reduction yields the following:
:&quot;The simple operations must therefore include:
::&quot;(a) Changes of the symbol on one of the observed squares
::&quot;(b) Changes of one of the squares observed to another square within L squares of one of the previously observed squares.
&quot;It may  be that some of these change necessarily invoke a change of state of mind. The most general single operation must therefore be taken to be one of the following:
::&quot;(A) A possible change (a) of symbol together with a possible change of state of mind.
::&quot;(B) A possible change (b) of observed squares, together with a possible change of state of mind&quot;

:&quot;We may  now construct a machine to do the work of this computer&quot;.&lt;ref name=&quot;Turing 1936-7 in Davis 1965:136&quot;/&gt;

A few years later, Turing expanded his analysis (thesis, definition) with this forceful expression of it:
:&quot;A function is said to be &quot;effectively calculable&quot; if its values can be found by some purely mechanical process. Although it is fairly easy to get an intuitive grasp of this idea, it is nevertheless desirable to have some more definite, mathematical expressible definition . . . [he discusses the history of the definition pretty much as presented above with respect to Gödel, Herbrand, Kleene, Church, Turing and Post] . . . We may  take this statement literally, understanding by a purely mechanical process one which could be carried out by a machine. It is possible to give a mathematical description, in a certain normal form, of the structures of these machines. The development of these ideas leads to the author's definition of a computable function, and to an identification of computability † with effective calculability . . . .
::&quot;† We shall use the expression &quot;computable function&quot; to mean a function calculable by a machine, and we let &quot;effectively calculable&quot; refer to the intuitive idea without particular identification with any one of these definitions&quot;.&lt;ref&gt;Turing 1939 in Davis 1965:160&lt;/ref&gt;

=== J. B. Rosser (1939) and S. C. Kleene (1943) ===
'''[[J. Barkley Rosser]]''' defined an 'effective [mathematical] method' in the following manner (boldface added):
:&quot;'Effective method' is used here in the rather special sense of a method each step of which is precisely determined and which is certain to produce the answer in a finite number of steps. With this special meaning, three different precise definitions have been given to date. [his footnote #5; see discussion immediately below]. The simplest of these to state (due to Post and Turing) says essentially that '''an effective method of solving certain sets of problems exists if one can build a machine which will then solve any problem of the set with no human intervention beyond inserting the question and (later) reading the answer'''. All three definitions are equivalent, so it doesn't matter which one is used. Moreover, the fact that all three are equivalent is a very strong argument for the correctness of any one.&quot; (Rosser 1939:225–6)

Rosser's footnote #5 references the work of (1) Church and Kleene and their definition of λ-definability, in particular Church's use of it in his ''An Unsolvable Problem of Elementary Number Theory'' (1936); (2) Herbrand and Gödel and their use of recursion in particular Gödel's use in his famous paper ''On Formally Undecidable Propositions of Principia Mathematica and Related Systems I'' (1931); and (3) Post (1936) and Turing (1936–7) in their mechanism-models of computation.

'''[[Stephen C. Kleene]]''' defined as his now-famous &quot;Thesis I&quot; known as the [[Church–Turing thesis]]. But he did this in the following context (boldface in original):
:&quot;12. '''Algorithmic theories'''... In setting up a complete algorithmic theory, what we do is to describe a procedure, performable for each set of values of the independent variables, which procedure necessarily terminates and in such manner that from the outcome we can read a definite answer, &quot;yes&quot; or &quot;no,&quot; to the question, &quot;is the predicate value true?&quot;&quot; (Kleene 1943:273)

=== History after 1950 ===
A number of efforts have been directed toward further refinement of the definition of &quot;algorithm&quot;, and activity is on-going because of issues surrounding, in particular, [[foundations of mathematics]] (especially the [[Church–Turing thesis]]) and [[philosophy of mind]] (especially arguments around [[artificial intelligence]]). For more, see [[Algorithm characterizations]].

== See also ==
&lt;div style=&quot;-moz-column-count:3; column-count:3;&quot;&gt;
* [[Abstract machine]]
* [[Algorithm engineering]]
* [[Algorithmic composition]]
* [[Algorithmic trading]]
* [[Garbage in, garbage out]]
* [[High-level synthesis]] Algorithmic synthesis
* [[List of important publications in theoretical computer science#Algorithms|List of important publications in theoretical computer science - Algorithms]]
* ''[[Introduction to Algorithms]]''
* [[List of algorithm general topics]]
* [[Numerical Mathematics Consortium]]
* [[Theory of computation]]
**[[Computability theory]]
** [[Computational complexity theory]]
&lt;/div&gt;

== Notes ==
{{Reflist|30em}}

== References ==
{{refbegin|30em}}
* Axt, P. (1959) On a Subrecursive Hierarchy and Primitive Recursive Degrees, ''Transactions of the American Mathematical Society'' 92, pp.&amp;nbsp;85–105
*Bell, C. Gordon and Newell, Allen (1971), ''Computer Structures: Readings and Examples'', McGraw-Hill Book Company, New York. ISBN 0-07-004357-4}.
* {{Cite journal|author1-link=Andreas Blass|first1=Andreas|last1=Blass|author2-link=Yuri Gurevich|first2=Yuri|last2=Gurevich|year=2003|url=http://research.microsoft.com/~gurevich/Opera/164.pdf|title=Algorithms: A Quest for Absolute Definitions|journal= Bulletin of European Association for Theoretical Computer Science|volume= 81}} Includes an excellent bibliography of 56 references.
* {{Cite book|last1=Boolos|first1= George|last2=Jeffrey|first2= Richard|author1-link=George Boolos|author2-link=Richard Jeffrey|title=Computability and Logic|edition=4th|publisher=Cambridge University Press, London|year=1974, 1999|date=1974, 1980, 1989, 1999|isbn=0-521-20402-X|ref=harv|author=George Boolos, Richard Jeffrey.}}: cf. Chapter 3 ''Turing machines'' where they discuss &quot;certain enumerable sets not effectively (mechanically) enumerable&quot;.
* Burgin, M. ''Super-recursive algorithms'', Monographs in computer science, Springer, 2005. ISBN 0-387-95569-0
* Campagnolo, M.L., [[Cris Moore|Moore, C.]], and Costa, J.F. (2000) An analog characterization of the subrecursive functions. In ''Proc. of the 4th Conference on Real Numbers and Computers'', Odense University, pp.&amp;nbsp;91–109
* {{Cite journal|last=Church|first=Alonzo|authorlink=Alonzo Church|title=An Unsolvable Problem of Elementary Number Theory|journal=The American Journal of Mathematics|volume=58|pages= 345–363|year=1936a|doi=10.2307/2371045|issue=2|jstor=2371045}} Reprinted in ''The Undecidable'', p.&amp;nbsp;89ff. The first expression of &quot;Church's Thesis&quot;. See in particular page 100 (''The Undecidable'') where he defines the notion of &quot;effective calculability&quot; in terms of &quot;an algorithm&quot;, and he uses the word &quot;terminates&quot;, etc.
* {{Cite journal|last=Church|first=Alonzo|authorlink=Alonzo Church|title=A Note on the Entscheidungsproblem|journal=The Journal of Symbolic Logic|volume=1|number=1|year=1936b|pages=40–41|doi=10.2307/2269326|issue=1|jstor=2269326}} {{cite journal|last=Church|first=Alonzo|title=Correction to a Note on the Entscheidungsproblem|journal=The Journal of Symbolic Logic|volume=1|number=3|year=1936|pages=101–102|doi=10.2307/2269030|issue=3|jstor=2269030}} Reprinted in ''The Undecidable'', p.&amp;nbsp;110ff. Church shows that the Entscheidungsproblem is unsolvable in about 3 pages of text and 3 pages of footnotes.
* {{Cite book|last=Daffa'|first=Ali Abdullah al-|title=The Muslim contribution to mathematics|year=1977|publisher=Croom Helm|location=London|isbn=0-85664-464-1}}
* {{Cite book|last=Davis|first=Martin|authorlink=Martin Davis|title=The Undecidable: Basic Papers On Undecidable Propositions, Unsolvable Problems and Computable Functions|publisher=Raven Press|location=New York|year=1965|isbn=0-486-43228-9}} Davis gives commentary before each article. Papers of [[Gödel]], [[Alonzo Church]], [[Alan Turing|Turing]], [[J. Barkley Rosser|Rosser]], [[Kleene]], and [[Emil Post]] are included; those cited in the article are listed here by author's name.
* {{Cite book|last=Davis|first=Martin|authorlink=Martin Davis|title=Engines of Logic: Mathematicians and the Origin of the Computer|publisher=W. W. Nortion|location=New York|year=2000|isbn=0-393-32229-7}} Davis offers concise biographies of [[Gottfried Leibniz|Leibniz]], [[George Boole|Boole]], [[Gottlob Frege|Frege]], [[Georg Cantor|Cantor]], [[David Hilbert|Hilbert]], Gödel and Turing with [[John von Neumann|von Neumann]] as the show-stealing villain. Very brief bios of [[Joseph-Marie Jacquard]], [[Babbage]], [[Ada Lovelace]], [[Claude Shannon]], [[Howard Aiken]], etc.
* {{DADS|algorithm|algorithm}}
* { Cite book|last=Dennett|first=Daniel|authorlink=Daniel Dennett|title=Darwin's Dangerous Idea|publisher=Touchstone/Simon &amp; Schuster|location=New York|year=1995|isbn=0-684-80290-2}}
* [[Yuri Gurevich]], [http://research.microsoft.com/~gurevich/Opera/141.pdf ''Sequential Abstract State Machines Capture Sequential Algorithms''], ACM Transactions on Computational Logic, Vol 1, no 1 (July 2000), pages 77–111. Includes bibliography of 33 sources.
* {{Cite journal|last=Kleene C.|first=Stephen|authorlink=Stephen Kleene |title=General Recursive Functions of Natural Numbers|journal=Mathematische Annalen|volume=112|pages=727–742|year=1936
 | doi = 10.1007/BF01565439|issue=5}} Presented to the American Mathematical Society, September 1935. Reprinted in ''The Undecidable'', p.&amp;nbsp;237ff. Kleene's definition of &quot;general recursion&quot; (known now as mu-recursion) was used by Church in his 1935 paper ''An Unsolvable Problem of Elementary Number Theory'' that proved the &quot;decision problem&quot; to be &quot;undecidable&quot; (i.e., a negative result).
* {{Cite journal|last=Kleene C.|first=Stephen|authorlink=Stephen Kleene |title= Recursive Predicates and Quantifiers|journal=American Mathematical Society Transactions|volume=54|number=1|pages=41–73|year=1943 |doi= 10.2307/1990131|issue=1|jstor=1990131}} Reprinted in ''The Undecidable'', p.&amp;nbsp;255ff. Kleene refined his definition of &quot;general recursion&quot; and proceeded in his chapter &quot;12. Algorithmic theories&quot; to posit &quot;Thesis I&quot; (p.&amp;nbsp;274); he would later repeat this thesis (in Kleene 1952:300) and name it &quot;Church's Thesis&quot;(Kleene 1952:317) (i.e., the [[Church thesis]]).
* {{Cite book|last=Kleene|first=Stephen C.|authorlink=Kleene|title=Introduction to Metamathematics|edition=Tenth Edition 1991|publisher=North-Holland Publishing Company|year=First Edition 1952|isbn=0-7204-2103-9}} Excellent—accessible, readable—reference source for mathematical &quot;foundations&quot;.
* {{Cite book|last=Knuth|first=Donald|authorlink=Donald Knuth|title=Fundamental Algorithms, Third Edition|publisher=Addison–Wesley|location=Reading, Massachusetts|year=1997|isbn=0-201-89683-4}}
* {{Cite book|last=Knuth|first=Donald|authorlink=Donald Knuth|title=Volume 2/Seminumerical Algorithms, The Art of Computer Programming First Edition|publisher=Addison–Wesley|location=Reading, Massachusetts|year=1969|isbn= }}
* Kosovsky, N. K. ''Elements of Mathematical Logic and its Application to the theory of Subrecursive Algorithms'', LSU Publ., Leningrad, 1981
* {{Cite journal|last=Kowalski|first=Robert|authorlink=Robert Kowalski|title=Algorithm=Logic+Control|journal=[[Communications of the ACM]]|volume=22|issue=7|pages=424–436|year=1979|doi=10.1145/359131.359136}}
* [[A. A. Markov]] (1954) ''Theory of algorithms''. [Translated by Jacques J. Schorr-Kon and PST staff] Imprint Moscow, Academy of Sciences of the USSR, 1954  i.e., Jerusalem, Israel Program for Scientific Translations, 1961; available from the Office of Technical Services, U.S. Dept. of Commerce, Washington] Description 444 p.&amp;nbsp;28&amp;nbsp;cm. Added t.p. in Russian Translation of Works of the Mathematical Institute, Academy of Sciences of the USSR, v. 42. Original title: Teoriya algerifmov. [QA248.M2943 Dartmouth College library. U.S. Dept. of Commerce, Office of Technical Services, number OTS 60-51085.]
* {{Cite book|last=Minsky|first=Marvin|authorlink=Marvin Minsky|title=Computation: Finite and Infinite Machines|edition=First|publisher=Prentice-Hall, Englewood Cliffs, NJ|year=1967|isbn=0-13-165449-7}} Minsky expands his &quot;...idea of an algorithm—an effective procedure...&quot; in chapter 5.1 ''Computability, Effective Procedures and Algorithms. Infinite machines.&quot;
* {{Cite journal|last=Post|first=Emil|authorlink=Emil Post|title=Finite Combinatory Processes, Formulation I|journal=The Journal of Symbolic Logic|volume=1|year=1936|pages=103–105|doi=10.2307/2269031|issue=3|jstor=2269031}} Reprinted in ''The Undecidable'', p. 289ff. Post defines a simple algorithmic-like process of a man writing marks or erasing marks and going from box to box and eventually halting, as he follows a list of simple instructions. This is cited by Kleene as one source of his &quot;Thesis I&quot;, the so-called [[Church–Turing thesis]].
*{{Cite book|last=Rogers, Jr|first=Hartley|title=Theory of Recursive Functions and Effective Computability|publisher=The MIT Press|year=1987|isbn=0-262-68052-1 (pbk.)}}
* {{Cite journal|last=Rosser|first=J.B.|authorlink=J.B. Rosser|title=An Informal Exposition of Proofs of Godel's Theorem and Church's Theorem|journal=Journal of Symbolic Logic|volume= 4 |year=1939}} Reprinted in ''The Undecidable'', p.&amp;nbsp;223ff. Herein is Rosser's famous definition of &quot;effective method&quot;: &quot;...a method each step of which is precisely predetermined and which is certain to produce the answer in a finite number of steps... a machine which will then solve any problem of the set with no human intervention beyond inserting the question and (later) reading the answer&quot; (p.&amp;nbsp;225–226, ''The Undecidable'')
*{{Cite book|last=Scott|first=Michael L.|title=Programming Language Pragmatics |edition=3rd |publisher=Morgan Kaufmann Publishers/Elsevier|year=2009|isbn=978-0-12-374514-9}}
* {{Cite book|last=Sipser|first=Michael|title=Introduction to the Theory of Computation|publisher=PWS Publishing Company|year=2006|isbn=0-534-94728-X}}
* {{Cite book|last=Stone|first=Harold S.|title=Introduction to Computer Organization and Data Structures|edition=1972|publisher=McGraw-Hill, New York|isbn=0-07-061726-0|year=1972}} Cf. in particular the first chapter titled: ''Algorithms, Turing Machines, and Programs''. His succinct informal definition: &quot;...any sequence of instructions that can be obeyed by a robot, is called an ''algorithm''&quot; (p.&amp;nbsp;4).
*{{Cite book|last=Tausworthe|first=Robert C|title=Standardized Development of Computer Software Part 1 Methods|publisher=Prentice-Hall, Inc.|location=Englewood Cliffs NJ|year=1977|isbn=0-13-842195-1}}
* {{Cite journal|last=Turing|first=Alan M.|authorlink=A. M. Turing|title=On Computable Numbers, With An Application to the Entscheidungsproblem|journal=[[Proceedings of the London Mathematical Society]], Series 2|volume=42|pages= 230–265 |year=1936–7|doi=10.1112/plms/s2-42.1.230 }}. Corrections, ibid, vol. 43(1937) pp.&amp;nbsp;544–546. Reprinted in ''The Undecidable'', p.&amp;nbsp;116ff. Turing's famous paper completed as a Master's dissertation while at King's College Cambridge UK.
* {{Cite journal|last=Turing|first=Alan M.|authorlink=A. M. Turing|title=Systems of Logic Based on Ordinals|journal=Proceedings of the London Mathematical Society, Series 2|volume=45|pages=161–228|year=1939|doi=10.1112/plms/s2-45.1.161}} Reprinted in ''The Undecidable'', p.&amp;nbsp;155ff. Turing's paper that defined &quot;the oracle&quot; was his PhD thesis while at Princeton USA.
* [[United States Patent and Trademark Office]] (2006),  http://www.uspto.gov/web/offices/pac/mpep/documents/2100_2106_02.htm ''2106.02 **&gt;Mathematical Algorithms: 2100 Patentability''], Manual of Patent Examining Procedure (MPEP). Latest revision August 2006

=== Secondary references ===
* {{Cite book|last=Bolter|first=David J.|title=Turing's Man: Western Culture in the Computer Age|edition=1984|publisher=The University of North Carolina Press, Chapel Hill NC|isbn=0-8078-1564-0|year=1984}}, ISBN 0-8078-4108-0 pbk.
* {{Cite book|last=Dilson|first=Jesse|authorlink=Dilson|title=The Abacus|edition=(1968,1994)|publisher=St. Martin's Press, NY|isbn=0-312-10409-X|year=2007}}, ISBN 0-312-10409-X (pbk.)
* {{Cite book|last=van Heijenoort|first=Jean|authorlink=van Heijenoort|title=From Frege to Gödel, A Source Book in Mathematical Logic, 1879–1931|edition=(1967)|publisher=Harvard University Press, Cambridge, MA|isbn=0-674-32449-8|year=2001}}, 3rd edition 1976[?], ISBN 0-674-32449-8 (pbk.)
* {{Cite book|last=Hodges|first=Andrew|title=Alan Turing: The Enigma|edition=(1983)|publisher=Simon and Schuster, New York|isbn=0-671-49207-1|year=1983}}, ISBN 0-671-49207-1. Cf. Chapter &quot;The Spirit of Truth&quot; for a history leading to, and a discussion of, his proof.
{{refend}}

== Further reading ==
{{refbegin}}
* Jean-Luc Chabert, Évelyne Barbin, ''A history of algorithms: from the pebble to the microchip'', Springer, 1999, ISBN 3-540-63369-3
* David Harel, Yishai A. Feldman, ''Algorithmics: the spirit of computing'', Edition 3, Pearson Education, 2004, ISBN 0-321-11784-0
* [[Donald Knuth|Knuth, Donald E.]] (2000). ''[http://www-cs-faculty.stanford.edu/~uno/aa.html Selected Papers on Analysis of Algorithms]''. Stanford, California: Center for the Study of Language and Information.
*Knuth, Donald E. (2010). ''[http://www-cs-faculty.stanford.edu/~uno/da.html Selected Papers on Design of Algorithms]''. Stanford, California: Center for the Study of Language and Information.
* [[David Berlinski]], ''The Advent of the Algorithm: The 300-Year Journey from an Idea to the Computer'', Mariner Books, 2001, ISBN 978-0-15-601391-8
{{refend}}

== External links ==
{{wikibooks|Algorithms}}
{{WVD}}
* {{dmoz|Computers/Algorithms/|Algorithms}}
* {{MathWorld | urlname=Algorithm | title=Algorithm}}
* [http://www.nist.gov/dads/ Dictionary of Algorithms and Data Structures]—[[National Institute of Standards and Technology]]
* [http://www.softpanorama.org/Algorithms/index.shtml Algorithms and Data Structures by Dr Nikolai Bezroukov]
; Algorithm repositories
* [http://www.cs.sunysb.edu/~algorith/ The Stony Brook Algorithm Repository]—[[State University of New York at Stony Brook]]
* [http://www.netlib.org/ Netlib Repository]—[[University of Tennessee]] and [[Oak Ridge National Laboratory]]
* [http://calgo.acm.org/ Collected Algorithms of the ACM]—[[Association for Computing Machinery]]
* [http://www-cs-staff.stanford.edu/~knuth/sgb.html The Stanford GraphBase]—[[Stanford University]]
* [http://www.combinatorica.com/ Combinatorica]—[[University of Iowa]] and [[State University of New York at Stony Brook]]
* [http://www.algorithmic-solutions.com/ Library of Efficient Datastructures and Algorithms (LEDA)]—previously from [[Max-Planck-Institut für Informatik]]
; Lecture notes
* [http://compgeom.cs.uiuc.edu/~jeffe//teaching/algorithms/ Algorithms Course Materials]. Jeff Erickson. [[University of Illinois]].
{{Use mdy dates|date=August 2010}}
[[Category:Algorithms| ]]
[[Category:Arabic words and phrases]]
[[Category:Articles with example pseudocode]]
[[Category:Mathematical logic]]
[[Category:Theoretical computer science]]

{{Link GA|uk}}
{{Link FA|tt}}

[[af:Algoritme]]
[[ar:خوارزمية]]
[[an:Algorismo]]
[[as:এলগৰিথম আৰু ডেইটা ষ্ট্ৰাকচাৰ]]
[[ast:Algoritmu]]
[[az:Alqoritm]]
[[bn:অ্যালগরিদম]]
[[zh-min-nan:Ián-sǹg-hoat]]
[[be:Алгарытм]]
[[be-x-old:Альгарытм]]
[[bg:Алгоритъм]]
[[bs:Algoritam]]
[[ca:Algorisme]]
[[cs:Algoritmus]]
[[da:Algoritme]]
[[de:Algorithmus]]
[[et:Algoritm]]
[[el:Αλγόριθμος]]
[[es:Algoritmo]]
[[eo:Algoritmo]]
[[eu:Algoritmo]]
[[fa:الگوریتم]]
[[fr:Algorithmique]]
[[gl:Algoritmo]]
[[ko:알고리즘]]
[[hy:Ալգորիթմ]]
[[hi:अल्गोरिद्म]]
[[hr:Algoritam]]
[[io:Algoritmo]]
[[id:Algoritma]]
[[ia:Algorithmo]]
[[is:Reiknirit]]
[[it:Algoritmo]]
[[he:אלגוריתם]]
[[ka:ალგორითმი]]
[[kk:Алгоритм]]
[[ku:Algorîtma]]
[[ky:Алгоритм]]
[[lo:ຂັ້ນຕອນວິທີ]]
[[la:Algorithmus]]
[[lv:Algoritms]]
[[lb:Algorithmus]]
[[lt:Algoritmas]]
[[hu:Algoritmus]]
[[mk:Алгоритам]]
[[ml:അൽഗൊരിഥം]]
[[mr:अल्गोरिदम]]
[[arz:الجوريتم]]
[[ms:Algoritma]]
[[mn:Алгоритм]]
[[my:အယ်လ်ဂေါ်ရစ်သမ်]]
[[nl:Algoritme]]
[[ne:अल्गोरिदम]]
[[ja:アルゴリズム]]
[[no:Algoritme]]
[[nn:Algoritme]]
[[oc:Algoritme]]
[[mhr:Алгоритм]]
[[uz:Algoritm]]
[[pnb:الگورتھم]]
[[pl:Algorytm]]
[[pt:Algoritmo]]
[[kaa:Algoritm]]
[[ro:Algoritm]]
[[rue:Алґорітм]]
[[ru:Алгоритм]]
[[sah:Алгоритм]]
[[sq:Algoritmi]]
[[scn:Alguritmu]]
[[si:ඇල්ගොරිතම]]
[[simple:Algorithm]]
[[sk:Algoritmus]]
[[sl:Algoritem]]
[[sr:Алгоритам]]
[[sh:Algoritam]]
[[su:Algoritma]]
[[fi:Algoritmi]]
[[sv:Algoritm]]
[[tl:Algoritmo]]
[[ta:படிமுறைத் தீர்வு]]
[[tt:Алгоритм]]
[[te:అల్గారిథం]]
[[th:ขั้นตอนวิธี]]
[[tg:Алгоритм]]
[[tr:Algoritma]]
[[uk:Алгоритм]]
[[ur:الخوارزم]]
[[vi:Thuật toán]]
[[wa:Algorisse]]
[[war:Algoritmo]]
[[yi:אלגאריטם]]
[[zh-yue:演算法]]
[[zh:算法]]</text>
      <sha1>lh245yrwmlja15wjugy6k29x2gph5un</sha1>
      <model>wikitext</model>
      <format>text/x-wiki</format>
    </revision>
  </page>
</mediawiki>")

