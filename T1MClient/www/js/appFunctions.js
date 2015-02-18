

/* File to hold larger functions that the litter survey requires */


function saveMetaDataToSurveyRecord(metaDataStorageKey, surveyStorageKey){
    var surveyRecord = angular.fromJson(window.localStorage.getItem(surveyStorageKey));

    var metaData = angular.fromJson(window.localStorage.getItem(metaDataStorageKey));
     
     angular.forEach(metaData, function(value, key){
        surveyRecord[key] = value;
     });
    
    if(surveyRecord.sli == null){
        surveyRecord.sli = "test";
    } 
    if(surveyRecord.sdt == null){
        surveyRecord.sdt = "1970-01-01";
    } 
    if(surveyRecord.edt == null){
        surveyRecord.edt = "1970-01-01";
    } 
    if(surveyRecord.obs == null){
        surveyRecord.obs = "test";
    } 
        surveyRecord.dst = [];
        surveyRecord.fld = [];
        surveyRecord.dat = [];
        surveyRecord.inf = [];
        surveyRecord.ind = [];
    
    window.localStorage.setItem(surveyStorageKey, angular.toJson(surveyRecord, false));
    
}

/* saves the given data sheet into the survey record file
    dataSheetType - the name of the data sheet (following the database data model)
    dataSheetSaveKey - the key of the key-value pair that data sheet is saved under in local storage.
    index - the index of the data sheet if it is already saved in the survey record.
*/
function saveDataSheetToSurveyRecord(dataSheetType, dataSheetStorageKey, surveyStorageKey) {
    var surveyRecord = angular.fromJson(window.localStorage.getItem(surveyStorageKey));
    
    window.localStorage.setItem(surveyStorageKey, angular.toJson(surveyRecord, false));
    
    if(surveyRecord==null){
        return;  
    }
    
    
    // get the given data sheet from local storage.
    var dataSheetData = angular.fromJson(window.localStorage.getItem(dataSheetStorageKey));
    
    
    if(dataSheetData==null){
        return;   
    }
    
    var dst = surveyRecord.dst;
    var fld = surveyRecord.fld;
    var dat = surveyRecord.dat;
    
    // make sure they exist before trying to populate them.
    if (dst == null){
        surveyRecord.dst = []; 
        dst = surveyRecord.dst;
    }
    
    if (fld == null){
        surveyRecord.fld = [];  
        fld = surveyRecord.fld;
    }
    
    if (dat == null){
        surveyRecord.dat = [];  
        dat = surveyRecord.dat;
    }
    
    dst.push(dataSheetType);
    
    var index = dst.length-1;
    
    var field = [];
    var data = [];
    
    // go through the data in the datasheet and add it to the arrays to be added to the survey record.
    angular.forEach(dataSheetData, function(value, key){
                if(key == "Instances"){
                    field.push(key);
                    // populate the inf and ind fields in survey record with the instaces
                    data.push(populateInstances(surveyRecord, value)); // returns the count of instances.
                } else {
                    field.push(key);
                    data.push(value);
                }
    });
    
    fld.push(field);
    dat.push(data);
    
    window.localStorage.setItem(surveyStorageKey, angular.toJson(surveyRecord, false));
    
}


/* method that will take a survey record and an array of instances and populate the inf and ind fields.
    surveyRecord - the surveyRecord that is to be populated
    instances - the array of instances to be added to the surveyRecord
*/
function populateInstances(surveyRecord, data){
    
    var instances = data.Instances;
    
    if(surveyRecord == null){
        return 0;   
    }
    if(instances == null){
        return 0;   
    }
    
    var inf = surveyRecord.inf;
    var ind = surveyRecord.ind;
    
    if (inf == null){
        surveyRecord.inf = [];   
        inf = surveyRecord.inf;
    }
    
    if (inf == null){
        surveyRecord.inf = [];   
        inf = surveyRecord.inf;
    }
    var count = 0; // count to return the number of instances added.
    
    // for each instance we populate a field and data array to be added to inf and ind
    for(var i = 0; i < instances.length; i++){
        var instance = angular.fromJson(window.localStorage.getItem(instances[i].saveName));
        var instanceField = [];
        var instanceData = [];
        for (field in instance){
            if(field == "ImageSrc"){
                var images = instance[field];
                for(var j = 0; j < images.length; j++){
                    var imageData = window.localStorage.getItem(images[i]);
                    if(imageData == null){ continue;}
                    instanceField.push("Image" + i);   
                    instanceData.push(imageData);
                }
                continue;
            }
            instanceField.push(field);
            instanceData.push(instance[field]);
        }
        inf.push(instanceField);
        ind.push(instanceData);
        count++
    }
    
    return count;
}

/* A function to load the survey record into a record Service and send the data to the server.
    recordService - a record service that has the server location. 
*/
function sendSurveyRecordToServer(recordService, surveyStorageKey){
    var surveyRecord = angular.fromJson(window.localStorage.getItem(surveyStorageKey), false);
    
    var necessaryFields = [
        {field:"sli", defaultValue:"test"},
        {field:"sdt", defaultValue:"test"},
        {field:"edt", defaultValue:"test"},
        {field:"obs", defaultValue:"test"},
        {field:"typ", defaultValue:"litterBeach"},
        {field:"dst", defaultValue:[]},
        {field:"fld", defaultValue:[]},
        {field:"dat", defaultValue:[]},
        {field:"inf", defaultValue:[]},
        {field:"ind", defaultValue:[]},
    ];
    
    //make sure the necessary fields in the survey record exist, so that they are sent to the server.
    //(only needed until validation exists.)
    for(field in necessaryFields){
        if(surveyRecord[field.field] == null){
            surveyRecord[field.field] = field.defaultValue;   
        }
    }
    
    angular.forEach(surveyRecord, function(value, key){
        recordService[key] = value; 
    });
    return recordService.$save();
}


var litterCodes = [
        {code:"PL01", type:"Plastic", value:"Bottle caps & lids"},
        {code:"PL02", type:"Plastic", value:"Bottles < 2 L"},
        {code:"PL03", type:"Plastic", value:"Bottles, drums, jerrycans & buckets > 2 L"},
        {code:"PL04", type:"Plastic", value:"Knives, forks, spoons, straws, stirrers, cutlery, lollypop sticks"},
        {code:"PL05", type:"Plastic", value:"Drink package rings, six-pack rings, ring carriers"},
        {code:"PL06", type:"Plastic", value:"Food containers (fast food, cups, lunch boxes & similar)"},
        {code:"PL07", type:"Plastic", value:"Plastic bags (opaque & clear)"},
        {code:"PL08", type:"Plastic", value:"Toys & party poppers"},
        {code:"PL09", type:"Plastic", value:"Gloves"},
        {code:"PL10", type:"Plastic", value:"Cigarette lighters"},
        {code:"PL11", type:"Plastic", value:"Cigarettes, butts & filters"},
        {code:"PL12", type:"Plastic", value:"Syringes"},
        {code:"PL13", type:"Plastic", value:"Baskets, crates & trays"},
        {code:"PL14", type:"Plastic", value:"Plastic buoys"},
        {code:"PL15", type:"Plastic", value:"Mesh bags (vegetable, oyster nets & mussel bags)"},
        {code:"PL16", type:"Plastic", value:"Sheeting (tarpaulin or other woven plastic bags, palette wrap)"},
        {code:"PL17", type:"Plastic", value:"Fishing gear (lures, traps & pots)"},
        {code:"PL18", type:"Plastic", value:"Monofilament line"},
        {code:"PL19", type:"Plastic", value:"Rope"},
        {code:"PL20", type:"Plastic", value:"Fishing net"},
        {code:"PL21", type:"Plastic", value:"Strapping"},
        {code:"PL22", type:"Plastic", value:"Fibreglass fragments"},
        {code:"PL23", type:"Plastic", value:"Resin pellets/nurdles"},
        {code:"PL24", type:"Plastic", value:"Other (specify)"},
        {code:"FP01", type:"Foamed Plastic", value:"Foam sponge"},
        {code:"FP02", type:"Foamed Plastic", value:"Cups & food packs"},
        {code:"FP03", type:"Foamed Plastic", value:"Foam buoys"},
        {code:"FP04", type:"Foamed Plastic", value:"Foam (insulation & packaging)"},
        {code:"FP05", type:"Foamed Plastic", value:"Other (specify)"},
        {code:"CL01", type:"Cloth", value:"Clothing, shoes, hats & towels"},
        {code:"CL02", type:"Cloth", value:"Backpacks & bags"},
        {code:"CL03", type:"Cloth", value:"Canvas, sailcloth & sacking (hessian)"},
        {code:"CL04", type:"Cloth", value:"Rope & string"},
        {code:"CL05", type:"Cloth", value:"Carpet & furnishing"},
        {code:"CL06", type:"Cloth", value:"Other cloth (including rags)"},
        {code:"GC01", type:"Glass & ceramic", value:"Construction material (brick, cement, pipes)"},
        {code:"GC02", type:"Glass & ceramic", value:"Bottles & jars"},
        {code:"GC03", type:"Glass & ceramic", value:"Tableware (plates & cups)"},
        {code:"GC04", type:"Glass & ceramic", value:"Light globes/bulbs"},
        {code:"GC05", type:"Glass & ceramic", value:"Fluorescent light tubes"},
        {code:"GC06", type:"Glass & ceramic", value:"Glass buoys"},
        {code:"GC07", type:"Glass & ceramic", value:"Glass or ceramic fragments"},
        {code:"GC08", type:"Glass & ceramic", value:"Other (specify)"},
        {code:"ME01", type:"Metal", value:"Tableware (plates, cups & cutlery)"},
        {code:"ME02", type:"Metal", value:"Bottle caps, lids & pull tabs"},
        {code:"ME03", type:"Metal", value:"Aluminium drink cans"},
        {code:"ME04", type:"Metal", value:"Other cans (< 4 L)"},
        {code:"ME05", type:"Metal", value:"Gas bottles, drums & buckets ( > 4 L)"},
        {code:"ME06", type:"Metal", value:"Foil wrappers"},
        {code:"ME07", type:"Metal", value:"Fishing related (sinkers, lures, hooks, traps & pots)"},
        {code:"ME08", type:"Metal", value:"Fragments"},
        {code:"ME09", type:"Metal", value:"Wire, wire mesh & barbed wire"},
        {code:"ME10", type:"Metal", value:"Other (specify), including appliances"},
        {code:"PC01", type:"Paper & cardboard", value:"Paper (including newspapers & magazines)"},
        {code:"PC02", type:"Paper & cardboard", value:"Cardboard boxes & fragments"},
        {code:"PC03", type:"Paper & cardboard", value:"Cups, food trays, food wrappers, cigarette packs, drink containers"},
        {code:"PC04", type:"Paper & cardboard", value:"Tubes for fireworks"},
        {code:"PC05", type:"Paper & cardboard", value:"Other (specify)"},
        {code:"RB01", type:"Rubber", value:"Balloons, balls & toys"},
        {code:"RB02", type:"Rubber", value:"Footwear (flip-flops)"},
        {code:"RB03", type:"Rubber", value:"Gloves"},
        {code:"RB04", type:"Rubber", value:"Tyres"},
        {code:"RB05", type:"Rubber", value:"Inner-tubes and rubber sheet"},
        {code:"RB06", type:"Rubber", value:"Rubber bands"},
        {code:"RB07", type:"Rubber", value:"Condoms"},
        {code:"RB08", type:"Rubber", value:"Other (specify)"},
        {code:"WD01", type:"Wood", value:"Corks"},
        {code:"WD02", type:"Wood", value:"Fishing traps and pots"},
        {code:"WD03", type:"Wood", value:"Ice-cream sticks, chip forks, chopsticks & toothpicks"},
        {code:"WD04", type:"Wood", value:"Processed timber and pallet crates"},
        {code:"WD05", type:"Wood", value:"Matches & fireworks"},
        {code:"WD06", type:"Wood", value:"Other (specify)"},
        {code:"OT01", type:"Other", value:"Paraffin or wax"},
        {code:"OT02", type:"Other", value:"Sanitary (nappies, cotton buds, tampon applicators, toothbrushes)"},
        {code:"OT03", type:"Other", value:"Appliances & Electronics"},
        {code:"OT04", type:"Other", value:"Batteries (torch type)"},
        {code:"OT05", type:"Other", value:"Other (specify)"}
    ];

function litterCodeSelectionByCode(code){
    var listCode;
    for (var i = 0; i < litterCodes.length; i++){
        listCode = litterCodes[i];
        if(listCode.code == code){
            return listCode;   
        }
    }
    return null;
}

function litterCodeSelection(type, specific){
    if(type == null){
         return selectFromSpecific(litterCodes, specific);  
    }
    
    var codeOptions = [];
    var code;
    for (var i = 0; i < litterCodes.length; i++){
        code = litterCodes[i];
        if(code.type == type){
            codeOptions.push(code);   
        }
    }
    return selectFromSpecific(codeOptions, specific);
    
}
            
function selectFromSpecific(codeOptions, specific){
    if(specific == null){
        return codeOptions;   
    }
    
    var codeOption = null;
    var code;
    
    for(var i = 0; i < codeOptions.length; i++){
        code = codeOptions[i];
        if(code.value == specific){
            codeOption = code;
            break;
        }
    }
    
    return codeOption;
}