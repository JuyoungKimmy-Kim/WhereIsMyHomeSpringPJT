export default {
    makeDateStr : function(year, month, day, type){
        return year + type + ( (month < 10) ? '0' + month : month ) + type + ( (day < 10) ? '0' + day : day );
    },
    makeTimeStr : function(hour, minute, second, type){
        return hour + type + ( (minute < 10) ? '0' + minute : minute ) + type + ( (second < 10) ? '0' + second : second );
    },

    numberToKorean(number) {
        var inputNumber = number < 0 ? false : number;
        var unitWords = ["", "억", "조", "경"];
        var splitUnit = 10000;
        var splitCount = unitWords.length;
        var resultArray = [];
        var resultString = "";

        for (var i = 0; i < splitCount; i++) {
        var unitResult =
            (inputNumber % Math.pow(splitUnit, i + 1)) / Math.pow(splitUnit, i);
        unitResult = Math.floor(unitResult);
        if (unitResult > 0) {
            resultArray[i] = unitResult;
        }
        }
        for (i = 0; i < resultArray.length; i++) {
            if (!resultArray[i]) continue;
            resultString = String(resultArray[i]) + unitWords[i] + resultString;
        }
        return resultString;
    }
}