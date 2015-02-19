

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
                //TODO need to add system to run through and store repeated data
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
    for (instance in instances){
        var instanceField = [];
        var instanceData = [];
        // inner for loop to add all the instance data to the arrays.
        angular.forEach(instance, function(value, key){
                instanceField.push(key);
                instanceData.push(value);
        });
        inf.push(instanceField);
        ind.push(instanceField);
        count++;
    }
    
    return count;
}


function testSaveDataSheetToSurveyRecord(){
    var testSurveyRecord =  {"sli":"AD175","sdt":"2015-02-04","edt":"2015-02-05","obs":"John Doe","typ":"litter","dst":[],"fld":[],"dat":[],"inf":[],"ind":[]};

    var currentSurveyRecord = angular.fromJson(window.localStorage.getItem("SurveyRecord"));
   
    //store the test data under the surveyRecord key.
    
    var testDataSheet = {
        "Season":"Summer", 
        "Instances":[
            {"LitterCode":"pk11", "Description":"plastic", "Count":20, "Weight":5}
        ]};
    
    
}
