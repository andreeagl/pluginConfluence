AJS.toInit(function(){

	
	AJS.$('#a-new').click(function(e) {
	    e.preventDefault();
	    
	    var employee = {
	        name: AJS.$('#input-name').val(),
	        team: AJS.$('#input-team').val(),
	        startDate: AJS.$('#input-start-date').val(),
	        endDate: AJS.$('#input-end-date').val()
	    }
	  
	    AJS.$.ajax({
	        type: 'post',
	        url: AJS.Data.get('context-path') + '/rest/test-rest/1.0/save-employee',
	        data: JSON.stringify(employee),
	        contentType: 'application/json; charset=utf-8',
	        dataType: "json",
	        success: function(e) {
	            var a = AJS.$('<a class="a-delete" href="#">[delete]</a>');
	            AJS.$(a).click(deleteClick);
	            var tr = AJS.$('<tr></tr>')
	            		.append(AJS.$('<td class="td-name"></td>').html(employee.name))
	            		.append(AJS.$('<td></td>').html(employee.team))
	            		.append(AJS.$('<td></td>').html(employee.startDate))
	            		.append(AJS.$('<td></td>').html(employee.endDate))
	            		.append(AJS.$('<td class="td-delete"></td>').append(a));
	            AJS.$('#table-employees').append(tr);
	            name: AJS.$('#input-name').val('');
		        team: AJS.$('#input-team').val('');
	            startDate: AJS.$('#input-start-date').val('');
	            endDate: AJS.$('#input-end-date').val('');
	        },
	        error: function(e) {
	            alert("Error executing ajax request.");
	        }
	    });
	    
	 });
	    
	    function deleteClick(e) {
			e.preventDefault();
			var tableRow = AJS.$(e.target).parent().parent();
			var name = tableRow.find('.td-name').html();
			AJS.$.ajax({
				type: 'post',
				url: contextPath + '/rest/test-rest/1.0/remove-employee',
				data: name,
	            contentType: 'application/json; charset=utf-8',
	            dataType: 'json',
	            success: function(e) {
	            	tableRow.remove();
	            },
				error: function(e) {
					alert("Error executing ajax request.");
				}
			});	
		}
	    
	    var deleteLinks = AJS.$('a.delete');
		for (var i = 0, max = deleteLinks.length; i < max; i++) {
			var link = deleteLinks[i];
			AJS.$(link).click(deleteClick);
		}
		
	});