/*
Copyright 2014 António Miranda, Caio Valente, Igor Topcin, Jorge Melegati, Thales Paiva, Victor Santos

This file is part of PhysUSP.

PhysUSP is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

PhysUSP is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with PhysUSP. If not, see <http://www.gnu.org/licenses/>.
*/

function calculateResults() {
	var form = $("#data");
	if(!form.valid()) return;
	
	var input = form.serialize();
	var url = form.attr("action");
	
	var tables = [$('#oxygenConsumptionRest'), $('#oxygenConsumptionDuringExercise'), $('#oxygenConsumptionPostExercise')];
	
	for(var i = 0; i < tables.length; i++) {
		var oxygenConsumption = new OxygenConsumptionTable(tables[i]);
		input += "&" + oxygenConsumption.getDataAsString();
	}

	$.ajax({
		url: url,
		type: "POST",
		data: input,
		success: onCalculateSuccess,
		error: onCalculateError
	});
}

function onCalculateSuccess(result) {
	var data = [], totals = { Kcal: 0, KJ: 0, LO2: 0 };
	var aerobicRow = new ResultRow($("#aerobicRow"), result.consumption.aerobic);
	var anaerobicLacticRow = new ResultRow($("#anaerobicLacticRow"), result.consumption.anaerobicLactic);
	var anaerobicAlacticRow = new ResultRow($("#anaerobicAlacticRow"), result.consumption.anaerobicAlactic);
	
	if(result.consumption.aerobic != undefined){
		data.push(["Aerobic", parseFloat(result.consumption.aerobic.Kcal)]);
		aerobicRow.showResult();
		totals.Kcal += parseFloat(result.consumption.aerobic.Kcal);
		totals.KJ += parseFloat(result.consumption.aerobic.KJ);
		totals.LO2 += parseFloat(result.consumption.aerobic.LO2);
	}
	else
		aerobicRow.hide();
	
	if(result.consumption.anaerobicLactic != undefined){
		data.push(["Anaerobic Lactic", parseFloat(result.consumption.anaerobicLactic.Kcal)]);
		anaerobicLacticRow.showResult();
		totals.Kcal += parseFloat(result.consumption.anaerobicLactic.Kcal);
		totals.KJ += parseFloat(result.consumption.anaerobicLactic.KJ);
		totals.LO2 += parseFloat(result.consumption.anaerobicLactic.LO2);
	}
	else
		anaerobicLacticRow.hide();
		
	if(result.consumption.anaerobicAlactic != undefined){
		data.push(["Anaerobic Alactic", parseFloat(result.consumption.anaerobicAlactic.Kcal)]);
		anaerobicAlacticRow.showResult();
		totals.Kcal += parseFloat(result.consumption.anaerobicAlactic.Kcal);
		totals.KJ += parseFloat(result.consumption.anaerobicAlactic.KJ);
		totals.LO2 += parseFloat(result.consumption.anaerobicAlactic.LO2);
	}
	else
		anaerobicAlacticRow.hide();

	var totalsRow = new ResultRow($("#totalsRow"), totals);
	totalsRow.showResult();
	
	showChart(data);
	showAdvancedResults(result);
	
	var content = $("#resultsTable").table2CSV({ delivery: 'value', separator: '\t' });
	$("#btnExportSystems").attr('href', 'data:application/csv;windows-1252,%FF%FE' + encodeURIComponent(content));
}

function onCalculateError(data) {
	$("#modal-error").find("#error-content").text("");
	if(data.responseJSON != undefined) {
		$("#modal-error").find("#error-content").text(data.responseJSON.exceptionInfo.message);
		var tabName = data.responseJSON.exceptionInfo.location;
		if(tabName != undefined)
			$("#containerTabs").find("a[href='#" + tabName + "']").tab("show");
	}
	$("#modal-error").modal("show");
}

function hasSelectedAnaerobicLactic(){
	return $("#parameters\\.calculateAnaerobicLactic").is(":checked");
}

function hasSelectedAnaerobicAlactic(){
	return $("#parameters\\.calculateAnaerobicAlactic").is(":checked");
}

function hasSelectedAerobic(){
	return $("#parameters\\.calculateAerobic").is(":checked");
}

function getRestOxygenCalculateMethod(){
	return $("[name='restOxygenParameters.calculateMethod']:checked").val();
}

function isRestOxygenFixed(){
	return getRestOxygenCalculateMethod() == "fixed";
}

$(function(){
	$("#data").validate({
		errorClass: "error",
		ignore: ":hidden:not(#oxygenConsumptionRestError,#oxygenConsumptionDuringExerciseError,#oxygenConsumptionPostExerciseError)",
		rules: {
			"anaerobicLacticParameters.weight": {
				required: hasSelectedAnaerobicLactic,
				min: 0.0001
			},
			"anaerobicLacticParameters.restLactateConcentration": {
				required: hasSelectedAnaerobicLactic,
				min: 0
			},
			"anaerobicLacticParameters.peakLactateConcentration": {
				required: hasSelectedAnaerobicLactic,
				greaterThan: "#anaerobicLacticParameters\\.restLactateConcentration"
			},
			"restOxygenParameters.fixedValue": {
				required: function() { return (hasSelectedAerobic() || hasSelectedAnaerobicAlactic()) && isRestOxygenFixed(); },
				min: 0
			},
			"oxygenConsumptionRestError": {
				empty: function() { return (hasSelectedAerobic() || hasSelectedAnaerobicAlactic()); }
			},
			"oxygenConsumptionDuringExerciseError": {
				empty: hasSelectedAerobic
			},
			"oxygenConsumptionPostExerciseError": {
				empty: hasSelectedAnaerobicAlactic
			}   
		},
		messages:{
			"anaerobicLacticParameters.weight": {
				min: "Please enter a value greater than or equal to 0."
			},
			"anaerobicLacticParameters.peakLactateConcentration": {
				greaterThan: "Must be greater than rest concentration."
			},
			"oxygenConsumptionRestError": {
				empty: function() { return $("#oxygenConsumptionRestError").val(); }
			},
			"oxygenConsumptionDuringExerciseError": {
				empty: function() { return $("#oxygenConsumptionDuringExerciseError").val(); }
			},
			"oxygenConsumptionPostExerciseError": {
				empty: function() { return $("#oxygenConsumptionPostExerciseError").val(); }
			} 
		}
	});
});
