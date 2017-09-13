import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kreps.app.data.CivicInfo
import kreps.app.data.Division
import kreps.app.data.GoogleCivicInfoJsonDeserializer
import org.junit.Before
import org.junit.Test
import java.io.StringReader
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class JsonDeserializationTests {

    val windingWayInfo = """{
 "kind": "civicinfo#representativeInfoResponse",
 "normalizedInput": {
  "line1": "3815 Winding Way",
  "city": "Cincinnati",
  "state": "OH",
  "zip": "45229"
 },
 "divisions": {
  "ocd-division/country:us": {
   "name": "United States",
   "officeIndices": [
    0,
    1
   ]
  },
  "ocd-division/country:us/state:oh": {
   "name": "Ohio",
   "officeIndices": [
    2,
    4,
    5,
    11,
    12,
    13,
    14,
    15
   ]
  },
  "ocd-division/country:us/state:oh/cd:1": {
   "name": "Ohio's 1st congressional district",
   "officeIndices": [
    3
   ]
  },
  "ocd-division/country:us/state:oh/county:hamilton": {
   "name": "Hamilton County",
   "officeIndices": [
    16,
    17,
    18,
    19,
    20,
    21,
    22,
    23,
    24,
    25
   ]
  },
  "ocd-division/country:us/state:oh/place:cincinnati": {
   "name": "Cincinnati city",
   "officeIndices": [
    8,
    9,
    10
   ]
  },
  "ocd-division/country:us/state:oh/place:cincinnati/ward:13": {
   "name": "Cincinnati OH ward 13"
  },
  "ocd-division/country:us/state:oh/sldl:33": {
   "name": "Ohio State House district 33",
   "officeIndices": [
    7
   ]
  },
  "ocd-division/country:us/state:oh/sldu:9": {
   "name": "Ohio State Senate district 9",
   "officeIndices": [
    6
   ]
  }
 },
 "offices": [
  {
   "name": "President of the United States",
   "divisionId": "ocd-division/country:us",
   "levels": [
    "country"
   ],
   "roles": [
    "headOfState",
    "headOfGovernment"
   ],
   "officialIndices": [
    0
   ]
  },
  {
   "name": "Vice-President of the United States",
   "divisionId": "ocd-division/country:us",
   "levels": [
    "country"
   ],
   "roles": [
    "deputyHeadOfGovernment"
   ],
   "officialIndices": [
    1
   ]
  },
  {
   "name": "United States Senate",
   "divisionId": "ocd-division/country:us/state:oh",
   "levels": [
    "country"
   ],
   "roles": [
    "legislatorUpperBody"
   ],
   "officialIndices": [
    2,
    3
   ]
  },
  {
   "name": "United States House of Representatives OH-01",
   "divisionId": "ocd-division/country:us/state:oh/cd:1",
   "levels": [
    "country"
   ],
   "roles": [
    "legislatorLowerBody"
   ],
   "officialIndices": [
    4
   ]
  },
  {
   "name": "Governor",
   "divisionId": "ocd-division/country:us/state:oh",
   "levels": [
    "administrativeArea1"
   ],
   "roles": [
    "headOfGovernment"
   ],
   "officialIndices": [
    5
   ]
  },
  {
   "name": "Lieutenant Governor",
   "divisionId": "ocd-division/country:us/state:oh",
   "levels": [
    "administrativeArea1"
   ],
   "roles": [
    "deputyHeadOfGovernment"
   ],
   "officialIndices": [
    6
   ]
  },
  {
   "name": "OH State Senate District 9",
   "divisionId": "ocd-division/country:us/state:oh/sldu:9",
   "levels": [
    "administrativeArea1"
   ],
   "roles": [
    "legislatorUpperBody"
   ],
   "officialIndices": [
    7
   ]
  },
  {
   "name": "OH State House District 33",
   "divisionId": "ocd-division/country:us/state:oh/sldl:33",
   "levels": [
    "administrativeArea1"
   ],
   "roles": [
    "legislatorLowerBody"
   ],
   "officialIndices": [
    8
   ]
  },
  {
   "name": "Mayor",
   "divisionId": "ocd-division/country:us/state:oh/place:cincinnati",
   "officialIndices": [
    9
   ]
  },
  {
   "name": "Council Member",
   "divisionId": "ocd-division/country:us/state:oh/place:cincinnati",
   "officialIndices": [
    10,
    11,
    12,
    13,
    14,
    15,
    16,
    17
   ]
  },
  {
   "name": "Council Member and Vice Mayor",
   "divisionId": "ocd-division/country:us/state:oh/place:cincinnati",
   "officialIndices": [
    18
   ]
  },
  {
   "name": "State Auditor",
   "divisionId": "ocd-division/country:us/state:oh",
   "officialIndices": [
    19
   ]
  },
  {
   "name": "State Treasurer",
   "divisionId": "ocd-division/country:us/state:oh",
   "officialIndices": [
    20
   ]
  },
  {
   "name": "Attorney General",
   "divisionId": "ocd-division/country:us/state:oh",
   "officialIndices": [
    21
   ]
  },
  {
   "name": "Secretary of State",
   "divisionId": "ocd-division/country:us/state:oh",
   "officialIndices": [
    22
   ]
  },
  {
   "name": "State Board of Education Member District At-Large",
   "divisionId": "ocd-division/country:us/state:oh",
   "officialIndices": [
    23,
    24,
    25,
    26,
    27,
    28,
    29,
    30
   ]
  },
  {
   "name": "Sheriff",
   "divisionId": "ocd-division/country:us/state:oh/county:hamilton",
   "officialIndices": [
    31
   ]
  },
  {
   "name": "Recorder",
   "divisionId": "ocd-division/country:us/state:oh/county:hamilton",
   "officialIndices": [
    32
   ]
  },
  {
   "name": "County Auditor",
   "divisionId": "ocd-division/country:us/state:oh/county:hamilton",
   "officialIndices": [
    33
   ]
  },
  {
   "name": "County Coroner",
   "divisionId": "ocd-division/country:us/state:oh/county:hamilton",
   "officialIndices": [
    34
   ]
  },
  {
   "name": "Clerk of Courts",
   "divisionId": "ocd-division/country:us/state:oh/county:hamilton",
   "officialIndices": [
    35
   ]
  },
  {
   "name": "County Engineer",
   "divisionId": "ocd-division/country:us/state:oh/county:hamilton",
   "officialIndices": [
    36
   ]
  },
  {
   "name": "County Treasurer",
   "divisionId": "ocd-division/country:us/state:oh/county:hamilton",
   "officialIndices": [
    37
   ]
  },
  {
   "name": "County Commission",
   "divisionId": "ocd-division/country:us/state:oh/county:hamilton",
   "officialIndices": [
    38,
    39,
    40
   ]
  },
  {
   "name": "Prosecuting Attorney",
   "divisionId": "ocd-division/country:us/state:oh/county:hamilton",
   "officialIndices": [
    41
   ]
  },
  {
   "name": "Hamilton County Common Pleas Court Judge",
   "divisionId": "ocd-division/country:us/state:oh/county:hamilton",
   "officialIndices": [
    42,
    43,
    44,
    45,
    46,
    47,
    48,
    49
   ]
  }
 ],
 "officials": [
  {
   "name": "Donald J. Trump",
   "address": [
    {
     "line1": "The White House",
     "line2": "1600 Pennsylvania Avenue NW",
     "city": "Washington",
     "state": "DC",
     "zip": "20500"
    }
   ],
   "party": "Republican",
   "phones": [
    "(202) 456-1111"
   ],
   "urls": [
    "http://www.whitehouse.gov/"
   ],
   "photoUrl": "https://www.whitehouse.gov/sites/whitehouse.gov/files/images/45/PE%20Color.jpg",
   "channels": [
    {
     "type": "GooglePlus",
     "id": "+whitehouse"
    },
    {
     "type": "Facebook",
     "id": "whitehouse"
    },
    {
     "type": "Twitter",
     "id": "potus"
    },
    {
     "type": "YouTube",
     "id": "whitehouse"
    }
   ]
  },
  {
   "name": "Mike Pence",
   "address": [
    {
     "line1": "The White House",
     "line2": "1600 Pennsylvania Avenue NW",
     "city": "Washington",
     "state": "DC",
     "zip": "20500"
    }
   ],
   "party": "Republican",
   "phones": [
    "(202) 456-1111"
   ],
   "urls": [
    "http://www.whitehouse.gov/"
   ],
   "photoUrl": "https://www.whitehouse.gov/sites/whitehouse.gov/files/images/45/VPE%20Color.jpg",
   "channels": [
    {
     "type": "GooglePlus",
     "id": "+whitehouse"
    },
    {
     "type": "Facebook",
     "id": "whitehouse"
    },
    {
     "type": "Twitter",
     "id": "VP"
    }
   ]
  },
  {
   "name": "Rob Portman",
   "address": [
    {
     "line1": "448 Russell Senate Office Building",
     "city": "Washington",
     "state": "DC",
     "zip": "20510"
    }
   ],
   "party": "Republican",
   "phones": [
    "(202) 224-3353"
   ],
   "urls": [
    "http://www.portman.senate.gov/public/"
   ],
   "photoUrl": "http://bioguide.congress.gov/bioguide/photo/P/P000449.jpg",
   "channels": [
    {
     "type": "Facebook",
     "id": "senrobportman"
    },
    {
     "type": "Twitter",
     "id": "senrobportman"
    },
    {
     "type": "YouTube",
     "id": "SenRobPortman"
    }
   ]
  },
  {
   "name": "Sherrod Brown",
   "address": [
    {
     "line1": "713 Hart Senate Office Building",
     "city": "Washington",
     "state": "DC",
     "zip": "20510"
    }
   ],
   "party": "Democratic",
   "phones": [
    "(202) 224-2315"
   ],
   "urls": [
    "http://www.brown.senate.gov/"
   ],
   "photoUrl": "http://bioguide.congress.gov/bioguide/photo/B/B000944.jpg",
   "channels": [
    {
     "type": "Twitter",
     "id": "SenSherrodBrown"
    },
    {
     "type": "YouTube",
     "id": "SherrodBrownOhio"
    },
    {
     "type": "Facebook",
     "id": "sherrod"
    },
    {
     "type": "GooglePlus",
     "id": "112542899483130793359"
    }
   ]
  },
  {
   "name": "Steve Chabot",
   "address": [
    {
     "line1": "2371 Rayburn House Office Building",
     "city": "Washington",
     "state": "DC",
     "zip": "20515"
    }
   ],
   "party": "Republican",
   "phones": [
    "(202) 225-2216"
   ],
   "urls": [
    "http://chabot.house.gov/"
   ],
   "photoUrl": "http://bioguide.congress.gov/bioguide/photo/C/C000266.jpg",
   "channels": [
    {
     "type": "GooglePlus",
     "id": "106956633685494001964"
    },
    {
     "type": "Facebook",
     "id": "RepSteveChabot"
    },
    {
     "type": "Twitter",
     "id": "repstevechabot"
    },
    {
     "type": "YouTube",
     "id": "congressmanstevechabot"
    }
   ]
  },
  {
   "name": "John R. Kasich",
   "address": [
    {
     "line1": "Riffe Center",
     "line2": "30th Floor",
     "line3": "77 South High Street",
     "city": "Columbus",
     "state": "OH",
     "zip": "43215"
    }
   ],
   "party": "Republican",
   "phones": [
    "(614) 466-3555"
   ],
   "urls": [
    "http://www.governor.ohio.gov/"
   ],
   "photoUrl": "http://governor.ohio.gov/portals/0/img/johnkasich.jpg",
   "channels": [
    {
     "type": "Twitter",
     "id": "OHPressSec"
    },
    {
     "type": "YouTube",
     "id": "governorjohnkasich"
    },
    {
     "type": "Facebook",
     "id": "JohnKasich"
    }
   ]
  },
  {
   "name": "Mary Taylor",
   "address": [
    {
     "line1": "Riffe Center",
     "line2": "30th Floor",
     "line3": "77 South High Street",
     "city": "Columbus",
     "state": "OH",
     "zip": "43215"
    }
   ],
   "party": "Republican",
   "phones": [
    "(614) 466-3555"
   ],
   "urls": [
    "http://www.governor.ohio.gov/About/LtGovernorTaylor.aspx"
   ],
   "photoUrl": "http://governor.ohio.gov/Portals/0/img/Mary_Taylor.jpg",
   "channels": [
    {
     "type": "Twitter",
     "id": "marytayloroh"
    }
   ]
  },
  {
   "name": "Cecil Thomas",
   "address": [
    {
     "line1": "1 CAPITOL SQ FL GROUND",
     "city": "Columbus",
     "state": "OH",
     "zip": "43215"
    }
   ],
   "party": "Democratic",
   "phones": [
    "(614) 466-5980"
   ],
   "urls": [
    "http://www.ohiosenate.gov/thomas"
   ],
   "photoUrl": "http://ohiosenate.gov/Assets/Headshots/Medium/9.jpg",
   "emails": [
    "sd09@ohiosenate.gov"
   ],
   "channels": [
    {
     "type": "Twitter",
     "id": "Thomas4Ohio"
    },
    {
     "type": "Facebook",
     "id": "Thomas4Ohio"
    }
   ]
  },
  {
   "name": "Alicia Reece",
   "address": [
    {
     "line1": "77 S HIGH ST FL 10",
     "city": "Columbus",
     "state": "OH",
     "zip": "43215"
    }
   ],
   "party": "Democratic",
   "phones": [
    "(614) 466-1308"
   ],
   "urls": [
    "http://www.aliciareece.com/"
   ],
   "photoUrl": "http://www.ohiohouse.gov/Assets/Headshots/Large/33.jpg",
   "emails": [
    "rep33@ohiohouse.gov"
   ],
   "channels": [
    {
     "type": "Twitter",
     "id": "aliciareece"
    },
    {
     "type": "Facebook",
     "id": "repaliciareece"
    }
   ]
  },
  {
   "name": "John Cranley",
   "address": [
    {
     "line1": "801 Plum St.",
     "city": "Cincinnati",
     "state": "OH",
     "zip": "45202"
    }
   ],
   "phones": [
    "(513) 352-3250"
   ],
   "urls": [
    "http://www.cincinnati-oh.gov/mayor/"
   ],
   "photoUrl": "http://www.cincinnati-oh.gov/mayor/assets/Image/Mayor-John-Cranley(1).jpg",
   "emails": [
    "mayor.cranley@cincinnati-oh.gov"
   ],
   "channels": [
    {
     "type": "Facebook",
     "id": "CranleyforCincinnati"
    },
    {
     "type": "Twitter",
     "id": "JohnCranley"
    },
    {
     "type": "YouTube",
     "id": "UCmZusovZ6N4kIrx-4qz7yqQ"
    }
   ]
  },
  {
   "name": "P.G. Sittenfeld",
   "address": [
    {
     "line1": "801 Plum St.",
     "line2": "Suite 354",
     "city": "Cincinnati",
     "state": "OH",
     "zip": "45202"
    }
   ],
   "phones": [
    "(513) 352-5270"
   ],
   "urls": [
    "http://www.cincinnati-oh.gov/sittenfeld/"
   ],
   "photoUrl": "http://www.cincinnati-oh.gov/sittenfeld/assets/Image/Sittenfeld-Picture.jpg",
   "emails": [
    "pg.sittenfeld@cincinnati-oh.gov"
   ],
   "channels": [
    {
     "type": "Facebook",
     "id": "pg.sittenfeld"
    },
    {
     "type": "Twitter",
     "id": "pgsittenfeld"
    }
   ]
  },
  {
   "name": "Charlie Winburn",
   "address": [
    {
     "line1": "801 Plum St.",
     "line2": "Suite 351",
     "city": "Cincinnati",
     "state": "OH",
     "zip": "45202"
    }
   ],
   "phones": [
    "(513) 352-5354"
   ],
   "urls": [
    "http://www.cincinnati-oh.gov/winburn/"
   ],
   "photoUrl": "http://www.cincinnati-oh.gov/council/assets/Image/Council%20Member%20Charlie%20Winburn.jpg",
   "emails": [
    "charlie.winburn@cincinnati-oh.gov"
   ],
   "channels": [
    {
     "type": "Facebook",
     "id": "charlie.winburn.cincinnati"
    },
    {
     "type": "Twitter",
     "id": "CharlieWinburn"
    }
   ]
  },
  {
   "name": "Amy Murray",
   "address": [
    {
     "line1": "801 Plum St.",
     "line2": "Suite 346A",
     "city": "Cincinnati",
     "state": "OH",
     "zip": "45202"
    }
   ],
   "phones": [
    "(513) 352-3640"
   ],
   "urls": [
    "http://www.cincinnati-oh.gov/council/council-members/council-member-amy-murray/"
   ],
   "photoUrl": "http://www.cincinnati-oh.gov/council/assets/Image/Amy-Murray.jpg",
   "emails": [
    "amy.murray@cincinnati-oh.gov"
   ],
   "channels": [
    {
     "type": "Facebook",
     "id": "ElectAmyMurray"
    },
    {
     "type": "Twitter",
     "id": "electamymurray"
    }
   ]
  },
  {
   "name": "Chris Seelbach",
   "address": [
    {
     "line1": "801 Plum St.",
     "line2": "Suite 350",
     "city": "Cincinnati",
     "state": "OH",
     "zip": "45202"
    }
   ],
   "phones": [
    "(513) 352-5210"
   ],
   "urls": [
    "http://www.cincinnati-oh.gov/seelbach/"
   ],
   "photoUrl": "http://www.cincinnati-oh.gov/seelbach/assets/Image/Seelbach.jpg",
   "emails": [
    "chris.seelbach@cincinnati-oh.gov"
   ],
   "channels": [
    {
     "type": "Facebook",
     "id": "ChrisSeelbach"
    },
    {
     "type": "Twitter",
     "id": "ChrisSeelbach"
    }
   ]
  },
  {
   "name": "Kevin Flynn",
   "address": [
    {
     "line1": "801 Plum St.",
     "line2": "Suite 358",
     "city": "Cincinnati",
     "state": "OH",
     "zip": "45202"
    }
   ],
   "phones": [
    "(513) 352-4550"
   ],
   "urls": [
    "http://www.cincinnati-oh.gov/council/council-members/council-member-kevin-flynn/"
   ],
   "photoUrl": "http://www.cincinnati-oh.gov/council/assets/Image/Kevin-Flynn.jpg",
   "emails": [
    "kevin.flynn@cincinnati-oh.gov"
   ],
   "channels": [
    {
     "type": "Twitter",
     "id": "FlynnForCincy"
    }
   ]
  },
  {
   "name": "Yvette Simpson",
   "address": [
    {
     "line1": "801 Plum St.",
     "city": "Cincinnati",
     "state": "OH",
     "zip": "45202"
    }
   ],
   "phones": [
    "(513) 352-5260"
   ],
   "urls": [
    "http://www.cincinnati-oh.gov/simpson/"
   ],
   "photoUrl": "http://www.cincinnati-oh.gov/simpson/assets/Image/Simpson-Picture.jpg",
   "emails": [
    "yvette.simpson@cincinnati-oh.gov"
   ],
   "channels": [
    {
     "type": "Facebook",
     "id": "Yvette-Simpson-for-Cincinnati-City-Council-146449818698875"
    },
    {
     "type": "Twitter",
     "id": "yvette4cincy"
    }
   ]
  },
  {
   "name": "Christopher Smitherman",
   "address": [
    {
     "line1": "801 Plum St.",
     "line2": "Suite 346B",
     "city": "Cincinnati",
     "state": "OH",
     "zip": "45202"
    }
   ],
   "phones": [
    "(513) 352-3464"
   ],
   "urls": [
    "http://www.cincinnati-oh.gov/council/council-members/council-member-christopher-smitherman/"
   ],
   "photoUrl": "http://www.cincinnati-oh.gov/council/assets/Image/Council%20Member%20Christopher%20Smitherman.jpg",
   "emails": [
    "christopher.smitherman@cincinnati-oh.gov"
   ],
   "channels": [
    {
     "type": "Facebook",
     "id": "Smitherman-for-Cincinnati-City-Council-121587647932940"
    },
    {
     "type": "Twitter",
     "id": "votesmitherman"
    }
   ]
  },
  {
   "name": "Wendell Young",
   "address": [
    {
     "line1": "801 Plum St.",
     "line2": "Suite 352",
     "city": "Cincinnati",
     "state": "OH",
     "zip": "45202"
    }
   ],
   "phones": [
    "(513) 352-3466"
   ],
   "urls": [
    "http://www.cincinnati-oh.gov/young/"
   ],
   "photoUrl": "http://www.cincinnati-oh.gov/young/assets/Image/Young.jpg",
   "emails": [
    "wendell.young@cincinnati-oh.gov"
   ],
   "channels": [
    {
     "type": "Facebook",
     "id": "councilmanwendellyoung"
    },
    {
     "type": "Twitter",
     "id": "wendellyoung"
    }
   ]
  },
  {
   "name": "David Mann",
   "address": [
    {
     "line1": "801 Plum St.",
     "line2": "Suite 356",
     "city": "Cincinnati",
     "state": "OH",
     "zip": "45202"
    }
   ],
   "phones": [
    "(513) 352-4610"
   ],
   "urls": [
    "http://www.cincinnati-oh.gov/council/council-members/vice-mayor-david-mann/"
   ],
   "photoUrl": "http://www.cincinnati-oh.gov/council/assets/Image/David-Mann-Photo.jpg",
   "emails": [
    "david.mann@cincinnati-oh.gov"
   ],
   "channels": [
    {
     "type": "Facebook",
     "id": "david.mann.33821"
    },
    {
     "type": "Twitter",
     "id": "dsmann115"
    }
   ]
  },
  {
   "name": "Dave Yost",
   "address": [
    {
     "line1": "88 East Broad Street",
     "line2": "5th Floor",
     "city": "Columbus",
     "state": "OH",
     "zip": "43215"
    }
   ],
   "party": "Republican",
   "phones": [
    "(614) 466-3402"
   ],
   "urls": [
    "https://ohioauditor.gov/"
   ],
   "channels": [
    {
     "type": "Facebook",
     "id": "OhioAuditor"
    },
    {
     "type": "Twitter",
     "id": "OhioAuditor"
    }
   ]
  },
  {
   "name": "Josh Mandel",
   "address": [
    {
     "line1": "30 E. Broad Street - 9th Floor",
     "city": "Columbus",
     "state": "OH",
     "zip": "43215"
    }
   ],
   "party": "Republican",
   "phones": [
    "(614) 466-2160"
   ],
   "urls": [
    "http://www.tos.ohio.gov/Biography"
   ],
   "channels": [
    {
     "type": "Facebook",
     "id": "ohiotreasurer"
    },
    {
     "type": "Twitter",
     "id": "ohiotreasurer"
    }
   ]
  },
  {
   "name": "Mike DeWine",
   "address": [
    {
     "line1": "30 E. Broad St.",
     "line2": "14th Floor",
     "city": "Columbus",
     "state": "OH",
     "zip": "43215"
    }
   ],
   "party": "Republican",
   "phones": [
    "(614) 466-4986"
   ],
   "urls": [
    "http://www.ohioattorneygeneral.gov/"
   ],
   "channels": [
    {
     "type": "Facebook",
     "id": "OhioAttorneyGeneral"
    },
    {
     "type": "Twitter",
     "id": "OhioAG"
    }
   ]
  },
  {
   "name": "Jon Husted",
   "address": [
    {
     "line1": "180 East Broad Street",
     "line2": "16th Floor",
     "city": "Columbus",
     "state": "OH",
     "zip": "43215"
    }
   ],
   "party": "Republican",
   "phones": [
    "(614) 466-2655"
   ],
   "urls": [
    "http://www.sos.state.oh.us/SOS/agency.aspx"
   ],
   "channels": [
    {
     "type": "Facebook",
     "id": "ohiososhusted"
    },
    {
     "type": "Twitter",
     "id": "OhioSOSHusted"
    }
   ]
  },
  {
   "name": "Melanie P. Bolender",
   "address": [
    {
     "line1": "25 S. Front Street Mail Stop 103",
     "city": "Columbus",
     "state": "OH",
     "zip": "43215"
    }
   ],
   "party": "Nonpartisan",
   "phones": [
    "(614) 466-9514"
   ],
   "urls": [
    "http://education.ohio.gov/State-Board/State-Board-of-Education-Home"
   ]
  },
  {
   "name": "Joseph L. Farmer",
   "address": [
    {
     "line1": "25 S. Front Street",
     "line2": "Mail Stop 103",
     "city": "Columbus",
     "state": "OH",
     "zip": "43215"
    }
   ],
   "party": "Nonpartisan",
   "phones": [
    "(614) 728-2754"
   ],
   "urls": [
    "http://education.ohio.gov/State-Board/State-Board-Members"
   ],
   "emails": [
    "Joe.Farmer@education.ohio.gov"
   ]
  },
  {
   "name": "Tess Elshoff",
   "address": [
    {
     "line1": "25 S. Front Street",
     "line2": "Mail Stop 103",
     "city": "Columbus",
     "state": "OH",
     "zip": "43215"
    }
   ],
   "party": "Nonpartisan",
   "phones": [
    "(614) 728-2754"
   ],
   "urls": [
    "http://education.ohio.gov/State-Board/State-Board-Members"
   ],
   "emails": [
    "ElshoffTess@education.ohio.gov"
   ],
   "channels": [
    {
     "type": "Twitter",
     "id": "tess_elshoff"
    }
   ]
  },
  {
   "name": "Kara Morgan",
   "address": [
    {
     "line1": "25 S. Front Street",
     "line2": "Mail Stop 103",
     "city": "Columbus",
     "state": "OH",
     "zip": "43215"
    }
   ],
   "party": "Nonpartisan",
   "phones": [
    "(614) 728-2754"
   ],
   "urls": [
    "http://education.ohio.gov/State-Board/State-Board-Members"
   ],
   "emails": [
    "Kara.Morgan@education.ohio.gov"
   ]
  },
  {
   "name": "Rebecca Vazquez-Skillings",
   "address": [
    {
     "line1": "25 S. Front Street",
     "line2": "Mail Stop 103",
     "city": "Columbus",
     "state": "OH",
     "zip": "43215"
    }
   ],
   "party": "Nonpartisan",
   "phones": [
    "(614) 728-2754"
   ],
   "urls": [
    "http://education.ohio.gov/State-Board/State-Board-Members"
   ],
   "emails": [
    "Rebecca.Vazquez-Skillings@education.ohio.gov"
   ]
  },
  {
   "name": "Frank Pettigrew",
   "address": [
    {
     "line1": "25 S. Front Street Mail Stop 103",
     "city": "Columbus",
     "state": "OH",
     "zip": "43215"
    }
   ],
   "party": "Nonpartisan",
   "phones": [
    "(614) 466-9514"
   ],
   "urls": [
    "http://education.ohio.gov/State-Board/State-Board-of-Education-Home"
   ]
  },
  {
   "name": "Cathye Flory",
   "address": [
    {
     "line1": "25 S. Front Street",
     "line2": "Mail Stop 103",
     "city": "Columbus",
     "state": "OH",
     "zip": "43215"
    }
   ],
   "party": "Nonpartisan",
   "phones": [
    "(614) 728-2754"
   ],
   "urls": [
    "http://education.ohio.gov/State-Board/State-Board-Members"
   ],
   "emails": [
    "Cathye.Flory@education.ohio.gov"
   ]
  },
  {
   "name": "C. Todd Jones",
   "address": [
    {
     "line1": "25 S. Front Street",
     "line2": "Mail Stop 103",
     "city": "Columbus",
     "state": "OH",
     "zip": "43215"
    }
   ],
   "party": "Nonpartisan",
   "phones": [
    "(614) 728-2754"
   ],
   "urls": [
    "http://education.ohio.gov/State-Board/State-Board-Members"
   ],
   "emails": [
    "Jones.Todd@education.ohio.gov"
   ]
  },
  {
   "name": "Jim Neil",
   "address": [
    {
     "line1": "1000 Sycamore St. Room 110",
     "city": "Cincinnati",
     "state": "OH",
     "zip": "45202"
    }
   ],
   "party": "Unknown",
   "phones": [
    "(513) 946-6400"
   ],
   "emails": [
    "mrobison@sheriff.hamilton-co.org"
   ]
  },
  {
   "name": "Norbert A. Nadel",
   "address": [
    {
     "line1": "138 E. Court Street",
     "city": "Cincinnati",
     "state": "OH",
     "zip": "45202"
    }
   ],
   "party": "Republican",
   "phones": [
    "(513) 946-4561"
   ],
   "urls": [
    "http://recordersoffice.hamilton-co.org"
   ]
  },
  {
   "name": "Dusty Rhodes",
   "address": [
    {
     "line1": "138 East Court Street,",
     "city": "Cincinnati",
     "state": "OH",
     "zip": "45202"
    }
   ],
   "party": "Unknown",
   "phones": [
    "(513) 946-4000"
   ],
   "urls": [
    "http://www.hamiltoncountyauditor.org"
   ]
  },
  {
   "name": "Lakshmi Kode Sammarco",
   "address": [
    {
     "line1": "3159 Eden Avenue",
     "city": "Cincinnati",
     "state": "OH",
     "zip": "45219"
    }
   ],
   "party": "Democratic",
   "phones": [
    "(513) 946-8700"
   ],
   "urls": [
    "http://www.hamiltoncountyohio.gov/coroner/"
   ]
  },
  {
   "name": "Aftab Pureval",
   "address": [
    {
     "line1": "1000 Main St",
     "city": "Cincinnati",
     "state": "OH",
     "zip": "45202"
    }
   ],
   "party": "Democratic",
   "phones": [
    "(513) 946-5656"
   ],
   "urls": [
    "http://www.courtclerk.org"
   ]
  },
  {
   "name": "Theodore B. Hubbard",
   "address": [
    {
     "line1": "138 East Court Street,",
     "city": "Cincinnati",
     "state": "OH",
     "zip": "45202"
    }
   ],
   "party": "Republican",
   "phones": [
    "(513) 946-4250"
   ]
  },
  {
   "name": "Robert A. Goering",
   "address": [
    {
     "line1": "PO Box 740857",
     "city": "Cincinnati",
     "state": "OH",
     "zip": "45274"
    }
   ],
   "party": "Republican",
   "phones": [
    "(513) 946-4800"
   ],
   "urls": [
    "http://www.hamiltoncountyohio.gov/government/departments/treasurer/"
   ]
  },
  {
   "name": "Chris Monzel",
   "address": [
    {
     "line1": "138 E. Court Street,",
     "city": "Cincinnati",
     "state": "OH",
     "zip": "45202"
    }
   ],
   "party": "Unknown",
   "phones": [
    "(513) 946-4409"
   ],
   "urls": [
    "http://www.hamiltoncountyohio.gov/government/departments/commissioners/commissioner_monzel"
   ],
   "emails": [
    "chris.monzel@hamilton-co.org"
   ]
  },
  {
   "name": "Todd Portune",
   "address": [
    {
     "line1": "138 E. Court Street,",
     "city": "Cincinnati",
     "state": "OH",
     "zip": "45202"
    }
   ],
   "party": "Unknown",
   "phones": [
    "(513) 946-4401"
   ],
   "urls": [
    "http://www.hamiltoncountyohio.gov/government/departments/commissioners/commissioner_portune"
   ],
   "emails": [
    "Todd.Portune@hamilton-co.org"
   ]
  },
  {
   "name": "Denise Driehaus",
   "address": [
    {
     "line1": "138 E. Court Street,",
     "city": "Cincinnati",
     "state": "OH",
     "zip": "45202"
    }
   ],
   "party": "Democratic",
   "phones": [
    "(513) 946-4406"
   ],
   "urls": [
    "http://www.hamiltoncountyohio.gov/government/departments/commissioners/commissioner_driehaus"
   ],
   "emails": [
    "dennis.deters@hamilton-co.org"
   ]
  },
  {
   "name": "Joseph T. Deters",
   "address": [
    {
     "line1": "230 E. Ninth Street",
     "city": "Cincinnati",
     "state": "OH",
     "zip": "45202"
    }
   ],
   "party": "Republican",
   "phones": [
    "(513) 946-3000"
   ],
   "urls": [
    "http://www.hcpros.org"
   ]
  },
  {
   "name": "Robert P. Ruehlman",
   "address": [
    {
     "line1": "1000 Main Street",
     "city": "Cincinnati",
     "state": "OH",
     "zip": "45202"
    }
   ],
   "phones": [
    "(513) 946-5851"
   ],
   "urls": [
    "http://hamiltoncountycourts.org/index.php/common-pleas-court-judge-robert-p-ruehlman-3/"
   ]
  },
  {
   "name": "Tom Heekin",
   "address": [
    {
     "line1": "1000 Main Street",
     "city": "Cincinnati",
     "state": "OH",
     "zip": "45202"
    }
   ],
   "phones": [
    "(513) 946-5785"
   ],
   "urls": [
    "http://hamiltoncountycourts.org/index.php/common-pleas-court-judge-tom-heekin"
   ]
  },
  {
   "name": "Melissa Powers"
  },
  {
   "name": "Jon H. Sieve",
   "address": [
    {
     "line1": "800 Broadway",
     "city": "Cincinnati",
     "state": "OH",
     "zip": "45202"
    }
   ],
   "phones": [
    "(513) 946-9000"
   ],
   "urls": [
    "http://www.hamilton-co.org/domestic/Judges/Judges.html"
   ]
  },
  {
   "name": "Susan Laker Tolbert",
   "address": [
    {
     "line1": "800 Broadway",
     "city": "Cincinnati",
     "state": "OH",
     "zip": "45202"
    }
   ],
   "phones": [
    "(513) 946-9000"
   ],
   "urls": [
    "http://www.hamilton-co.org/domestic/Judges/Judges.html"
   ]
  },
  {
   "name": "Jody Marie Luebbers",
   "address": [
    {
     "line1": "1000 Main Street",
     "city": "Cincinnati",
     "state": "OH",
     "zip": "45202"
    }
   ],
   "phones": [
    "(513) 946-5755"
   ],
   "urls": [
    "http://hamiltoncountycourts.org/index.php/common-pleas-court-judge-jody-m-luebbers/"
   ]
  },
  {
   "name": "Lisa Allen",
   "address": [
    {
     "line1": "1000 Main Street",
     "city": "Cincinnati",
     "state": "OH",
     "zip": "45202"
    }
   ],
   "phones": [
    "(513) 946-5112"
   ],
   "urls": [
    "http://hamiltoncountycourts.org/index.php/common-pleas-court-judge-lisa-c-allen/"
   ]
  },
  {
   "name": "Megan Shanahan",
   "address": [
    {
     "line1": "1000 Main Street",
     "city": "Cincinnati",
     "state": "OH",
     "zip": "45202"
    }
   ],
   "phones": [
    "(513) 946-5750"
   ],
   "urls": [
    "http://hamiltoncountycourts.org/index.php/common-pleas-court-judge-megan-e-shanahan/"
   ]
  }
 ]
}"""

    lateinit var gson : Gson

    @Before
    fun setupGsonBuilder() {
        val builder = GsonBuilder()
        builder.registerTypeAdapter(CivicInfo::class.java, GoogleCivicInfoJsonDeserializer())
        gson = builder.create()
    }

    @Test
    fun canRun() {
        assertTrue(true)
    }

    @Test
    fun canReadJson() {
        val x = gson.fromJson<CivicInfo>(StringReader(windingWayInfo), CivicInfo::class.java)
    }

    @Test
    fun canDeserializeListOfOffices() {
        val info = gson.fromJson<CivicInfo>(StringReader(windingWayInfo), CivicInfo::class.java)

        assertUnitedStatesDivision(info.divisions[0])
    }

    private fun assertUnitedStatesDivision(div: Division) {
        assertEquals("ocd-division/country:us", div.DivisionId)
        assertEquals("United States", div.Name)
        assertEquals(0, div.OfficeIndices[0])
        assertEquals(1, div.OfficeIndices[1])
    }
}
