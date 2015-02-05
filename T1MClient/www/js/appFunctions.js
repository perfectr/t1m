

/* File to hold larger functions that the litter survey requires */

/* saves the given data sheet into the survey record file
    dataSheetType - the name of the data sheet (following the database data model)
    dataSheetSaveKey - the key of the key-value pair that data sheet is saved under in local storage.
    index - the index of the data sheet if it is already saved in the survey record.
*/
function saveDataSheetToSurveyRecord(dataSheetType, dataSheetStorageKey) {
    var surveyRecord = angular.fromJson(window.localStorage.getItem("surveyRecord"));
    
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
    
    window.localStorage.setItem("surveyRecord", angular.toJson(surveyRecord, false));
    
}


/* method that will take a survey record and an array of instances and populate the inf and ind fields.
    surveyRecord - the surveyRecord that is to be populated
    instances - the array of instances to be added to the surveyRecord
*/
function populateInstances(surveyRecord, instances){
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
    for(i = 0; i < instances.length; i++){
        var instance = instances[i];
        var instanceField = [];
        var instanceData = [];
        for (field in instance){
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
function sendSurveyRecordToServer(recordService){
    var surveyRecord = angular.fromJson(window.localStorage.getItem("surveyRecord"), false);
    
    var necessaryFields = [
        {field:"sli", defaultValue:"null"},
        {field:"sdt", defaultValue:"null"},
        {field:"edt", defaultValue:"null"},
        {field:"obs", defaultValue:"null"},
        {field:"typ", defaultValue:"null"},
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
            surveyRecord[field.field] == field.defaultValue;   
        }
    }
    
    angular.forEach(surveyRecord, function(value, key){
        recordService[key] = value; 
    });
    return recordService.$save();
}

/*
    test function that adds test data including litter instances to the survey record.
*/
function testSaveDataSheetToSurveyRecord(){
    var testSurveyRecord =  {sli:"AD175",sdt:"2015-02-04",edt:"2015-02-05",obs:"John Doe",typ:"litter",dst:[],fld:[],dat:[],inf:[],ind:[]};

    var currentSurveyRecord = angular.fromJson(window.localStorage.getItem("surveyRecord"));
   
    //store the test data under the surveyRecord key.
    
    window.localStorage.setItem("surveyRecord", angular.toJson(testSurveyRecord, false));
    
    var testDataSheet = {
        Season:"Summer",
        Instances:[
            {LitterCode:"pk11",Description:"plastic11",Count:20,Weight:5},
            {LitterCode:"pk12",Description:"plastic12",Count:10,Weight:2},
            {LitterCode:"pk12",Description:"plastic12",Count:10,Weight:2, Notes:"there is soooooo much litter"}
        ]};
    
    window.localStorage.setItem("testDST", angular.toJson(testDataSheet, true));
    
    saveDataSheetToSurveyRecord("litterBeach", "testDST");
    
}
