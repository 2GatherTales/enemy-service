# Enemy Service Endpoints

#### api/enemy//findall

#### api/enemy/find/{id}

#### api/enemy/delete

#### Sample structure for api/enemy/create
```
{
       "id": 3,
       "type": "rogue",
       "abilityScores": {
           "F": "2.00",
           "D": "4.00",
           "S": "8.00"
       },
       "stats": {
           "hp": "[116, 120]",
           "str": "[4, 8]",
           "spd": "[4, 8]"
       },
       "patternLength": 7
   }
```
