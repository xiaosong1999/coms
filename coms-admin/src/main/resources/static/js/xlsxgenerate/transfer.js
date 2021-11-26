$(function(){
    String.prototype.replaceAll = function (FindText, RepText) {
		return this.replace(new RegExp(FindText, "g"), RepText);
	}
})

/**
 *
 * @param typeName  集合的泛型
 * @param str   该集合的json字符串
 * @param arr   要删除的属性
 * @param replaceAttrsJson  要代替的属性名(json格式)  name => 姓名;id =>编号 {'name':'姓名','id':'编号'}
 * @returns {*}
 */
function transferStrToJson(typeName,str,arr,replaceAttrsJson){
    var useLessAttr = [];
    if(arr != null){
        useLessAttr = arr;
    }
    useLessAttr.push('Hash');
    if(str.substring(0,4) == 'Page'){
        str = str.substring(str.indexOf("[")+1,str.lastIndexOf("]"));
    }
    else if(str.substr(0,1) == '['){
        str = str.substring(str.indexOf('[')+1,str.lastIndexOf(']'));
    }
    // console.log(str);
    str = str.replaceAll(typeName+" ","");
    str = str.replaceAll(" ","");
	str = str.replaceAll(/\[/g,"{'");
	str = str.replaceAll(/\]/g,"'}");
	str = str.replaceAll(":","/");
	str = str.replaceAll("=",":");
	str = str.replaceAll(":","':'");
	str = str.replaceAll("},{","}|{");
	str = str.replaceAll(",","','");
	str = str.replaceAll(/\|/g,",");
	str = '['+str+']';
	str = eval("("+str+")");
    for(var i in str){
        for(var j = 0;j<useLessAttr.length;j++){
            delete str[i][useLessAttr[j]];
        }
        for(var j in str[i]){
            for(var k in replaceAttrsJson){
                if(j == k){
                    str[i][replaceAttrsJson[k]] = str[i][j];
                    delete str[i][j];
                }
            }
        }
    }
	return str;
}