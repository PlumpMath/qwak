/**
 * The MIT License (MIT)
 * 
 * Copyright 2008-2013 Quine Interactive and other contributors
 * www.quineinteractive.com
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 */
package com.quine.qwak.tag;

import java.util.List;

/**
 * QwakJqGrid tag implementation.
 * 
 * @author Ivan Dejanovic
 * 
 * @version 1.0
 */
public class QwakJqGridTag extends QwakBasicTag {
	private String link;
	private String name;
	private String properties;
	private String columns;
	private String widths;
	private String additionalParameterNames;
	private String additionalParameterValues;
	private String onSuccessCallback;
	private boolean autoGenerateAddRow;
	private boolean autoGenerateEditRow;
	private boolean autoGenerateDeleteRow;
	private List<String> propertiesList;
	private List<String> columnsList;
	private List<String> widthsList;
	private List<String> additionalParameterNamesList;
	private List<String> additionalParameterValuesList;

	/**
	 * @return link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return properties
	 */
	public String getProperties() {
		return properties;
	}

	/**
	 * @param properties
	 */
	public void setProperties(String properties) {
		this.properties = properties;
	}

	/**
	 * @return columns
	 */
	public String getColumns() {
		return columns;
	}

	/**
	 * @param columns
	 */
	public void setColumns(String columns) {
		this.columns = columns;
	}

	/**
	 * @return widths
	 */
	public String getWidths() {
		return widths;
	}

	/**
	 * @param widths
	 */
	public void setWidths(String widths) {
		this.widths = widths;
	}

	/**
	 * @return the additionalParameterNames
	 */
	public String getAdditionalParameterNames() {
		return additionalParameterNames;
	}

	/**
	 * @param additionalParameterNames
	 *            the additionalParameterNames to set
	 */
	public void setAdditionalParameterNames(String additionalParameterNames) {
		this.additionalParameterNames = additionalParameterNames;
	}

	/**
	 * @return the additionalParameterValues
	 */
	public String getAdditionalParameterValues() {
		return additionalParameterValues;
	}

	/**
	 * @param additionalParameterValues
	 *            the additionalParameterValues to set
	 */
	public void setAdditionalParameterValues(String additionalParameterValues) {
		this.additionalParameterValues = additionalParameterValues;
	}

	/**
	 * @return the onSuccessCallback
	 */
	public String getOnSuccessCallback() {
		return onSuccessCallback;
	}

	/**
	 * @param onSuccessCallback
	 *            the onSuccessCallback to set
	 */
	public void setOnSuccessCallback(String onSuccessCallback) {
		this.onSuccessCallback = onSuccessCallback;
	}

	/**
	 * @return the autoGenerateAddRow
	 */
	public boolean isAutoGenerateAddRow() {
		return autoGenerateAddRow;
	}

	/**
	 * @param autoGenerateAddRow
	 *            the autoGenerateAddRow to set
	 */
	public void setAutoGenerateAddRow(boolean autoGenerateAddRow) {
		this.autoGenerateAddRow = autoGenerateAddRow;
	}

	/**
	 * @return the autoGenerateEditRow
	 */
	public boolean isAutoGenerateEditRow() {
		return autoGenerateEditRow;
	}

	/**
	 * @param autoGenerateEditRow
	 *            the autoGenerateEditRow to set
	 */
	public void setAutoGenerateEditRow(boolean autoGenerateEditRow) {
		this.autoGenerateEditRow = autoGenerateEditRow;
	}

	/**
	 * @return the autoGenerateDeleteRow
	 */
	public boolean isAutoGenerateDeleteRow() {
		return autoGenerateDeleteRow;
	}

	/**
	 * @param autoGenerateDeleteRow
	 *            the autoGenerateDeleteRow to set
	 */
	public void setAutoGenerateDeleteRow(boolean autoGenerateDeleteRow) {
		this.autoGenerateDeleteRow = autoGenerateDeleteRow;
	}

	protected void appendTagCode(StringBuilder sb) {
		setPropertiesToLists();

		if (autoGenerateAddRow) {
			appendAddRowCode(sb);
		}

		if (autoGenerateEditRow) {
			appendEditRowCode(sb);
		}

		if (autoGenerateDeleteRow) {
			appendDeleteRowCode(sb);
		}

		appendJqGridMainCode(sb);

		appendHtmlCode(sb);
	}

	protected void setPropertiesToLists() {
		propertiesList = propertyToList(properties);
		columnsList = propertyToList(columns);
		widthsList = propertyToList(widths);

		additionalParameterNamesList = null;
		if (additionalParameterNames != null) {
			additionalParameterNamesList = propertyToList(additionalParameterNames);
		}

		additionalParameterValuesList = null;
		if (additionalParameterValues != null) {
			additionalParameterValuesList = propertyToList(additionalParameterValues);
		}
	}

	protected void appendJqGridMainCode(StringBuilder sb) {
		sb.append("<script type=\"text/javascript\">\n");
		sb.append("$(function() {\n");
		sb.append("$('#" + name + "_grid').jqGrid({\n");

		int index = 0;
		StringBuilder additional = new StringBuilder();
		if (additionalParameterNamesList != null) {
			for (String name : additionalParameterNamesList) {
				additional.append("&" + name + "="
						+ additionalParameterValuesList.get(index++));
			}
		}

		sb.append("url:'" + link + "?list=true&gridname=" + name
				+ additional.toString() + "',\n");
		sb.append("datatype: 'json',\n");
		sb.append("mtype: 'GET',\n");
		StringBuilder columns = new StringBuilder();
		columns.append("colNames:['Id'");
		for (String column : columnsList) {
			columns.append(", '" + column + "'");
		}
		columns.append("],\n");
		sb.append(columns);
		sb.append("colModel:[\n");
		sb.append("{name:'id',index:'id', width:20,editable:false,editoptions:{readonly:true,size:10},hidden:true},\n");
		index = 0;
		for (String property : propertiesList) {
			sb.append("{name:'"
					+ property
					+ "',index:'"
					+ property
					+ "',  width:"
					+ widthsList.get(index++)
					+ ",editable:true, editrules:{required:true}, editoptions:{size:10}},\n");
		}
		sb.append("],\n");
		sb.append("postData: {\n");
		sb.append("},\n");
		sb.append("rowNum:20,\n");
		sb.append("rowList:[20,40,60],\n");
		sb.append("height: 200,\n");
		sb.append("autowidth: true,\n");
		sb.append("rownumbers: true,\n");
		sb.append("pager: '#" + name + "_pager',\n");
		sb.append("sortname: 'id',\n");
		sb.append("viewrecords: true,\n");
		sb.append("sortorder: 'asc',\n");
		sb.append("caption:'" + name + "',\n");
		sb.append("emptyrecords: 'Empty records',\n");
		sb.append("loadonce: false,\n");
		sb.append("loadComplete: function() {\n");
		sb.append("},\n");
		sb.append("jsonReader : {\n");
		sb.append("root: 'rows',\n");
		sb.append("page: 'page',\n");
		sb.append("total: 'total',\n");
		sb.append("records: 'records',\n");
		sb.append("repeatitems: false,\n");
		sb.append("cell: 'cell',\n");
		sb.append("id: 'id'\n");
		sb.append("}\n");
		sb.append("});\n");
		sb.append("$('#" + name + "_grid').jqGrid('navGrid','#" + name
				+ "_pager',\n");
		sb.append("{edit:false,add:false,del:false,search:true},\n");
		sb.append("{ },\n");
		sb.append("{ },\n");
		sb.append("{ },\n");
		sb.append("{\n");
		sb.append("sopt:['eq', 'ne', 'lt', 'gt', 'cn', 'bw', 'ew'],\n");
		sb.append("closeOnEscape: true,\n");
		sb.append("multipleSearch: true,\n");
		sb.append("closeAfterSearch: true }\n");
		sb.append(");\n");
		sb.append("$('#" + name + "_grid').navButtonAdd('#" + name
				+ "_pager',\n");
		sb.append("{	caption:'Add',\n");
		sb.append("buttonicon:'ui-icon-plus',\n");
		sb.append("onClickButton: addRow" + name + ",\n");
		sb.append("position: 'last',\n");
		sb.append("title:'',\n");
		sb.append("cursor: 'pointer'\n");
		sb.append("}\n");
		sb.append(");\n");
		sb.append("$('#" + name + "_grid').navButtonAdd('#" + name
				+ "_pager',\n");
		sb.append("{	caption:'Edit',\n");
		sb.append("buttonicon:'ui-icon-pencil',\n");
		sb.append("onClickButton: editRow" + name + ",\n");
		sb.append("position: 'last',\n");
		sb.append("title:'',\n");
		sb.append("cursor: 'pointer'\n");
		sb.append("}\n");
		sb.append(");\n");
		sb.append("$('#" + name + "_grid').navButtonAdd('#" + name
				+ "_pager',\n");
		sb.append("{	caption:'Delete',\n");
		sb.append("buttonicon:'ui-icon-trash',\n");
		sb.append("onClickButton: deleteRow" + name + ",\n");
		sb.append("position: 'last',\n");
		sb.append("title:'',\n");
		sb.append("cursor: 'pointer'\n");
		sb.append("}\n");
		sb.append(");\n");
		sb.append("$('#btnFilter').click(function(){\n");
		sb.append("$('#" + name + "_grid').jqGrid('searchGrid',\n");
		sb.append("{	multipleSearch: false,\n");
		sb.append("sopt:['eq']\n");
		sb.append("}\n");
		sb.append(");\n");
		sb.append("});\n");
		sb.append("});\n");
		sb.append("</script>\n");
	}

	protected void appendAddRowCode(StringBuilder sb) {
		int index = 0;
		StringBuilder additional = new StringBuilder();
		if (additionalParameterNamesList != null) {
			for (String name : additionalParameterNamesList) {
				additional.append("&" + name + "="
						+ additionalParameterValuesList.get(index++));
			}
		}

		sb.append("<script type=\"text/javascript\">\n");
		sb.append("function addRow" + name + "() {\n");
		sb.append("$('#" + name + "_grid').jqGrid('editGridRow','new', {\n");
		sb.append("url: '" + link + "?add=true&gridname=" + name
				+ additional.toString() + "',\n");
		sb.append("editData: {\n");
		sb.append("},\n");
		sb.append("recreateForm: true,\n");
		sb.append("beforeShowForm: function(form) {\n");
		sb.append("},\n");
		sb.append("closeAfterAdd: true,\n");
		sb.append("reloadAfterSubmit:true,\n");
		sb.append("afterSubmit : function(response, postdata) {\n");
		sb.append("var result = eval('(' + response.responseText + ')');\n");
		sb.append("var errors = '';\n");
		sb.append("if (result.success == false) {\n");
		sb.append("for (var i = 0; i < result.message.length; i++) {\n");
		sb.append("errors +=  result.message[i] + ' ';\n");
		sb.append("}\n");
		sb.append("}\n");
		sb.append("else {\n");
		if (onSuccessCallback != null && !onSuccessCallback.isEmpty()) {
			sb.append(onSuccessCallback + "('add');\n");
		}
		sb.append("$('#" + name
				+ "_dialog').text('Entry has been added successfully');\n");
		sb.append("$('#" + name + "_dialog').dialog( {\n");
		sb.append("title: 'Success',\n");
		sb.append("modal: true,\n");
		sb.append("buttons: {\n");
		sb.append("'Ok': function() {\n");
		sb.append("$(this).dialog('close');\n");
		sb.append("}\n");
		sb.append("}\n");
		sb.append("});\n");
		sb.append("}\n");
		sb.append("var new_id = null;\n");
		sb.append("return [result.success, errors, new_id];\n");
		sb.append("}\n");
		sb.append("});\n");
		sb.append("}\n");
		sb.append("</script>\n");
	}

	protected void appendEditRowCode(StringBuilder sb) {
		int index = 0;
		StringBuilder additional = new StringBuilder();
		if (additionalParameterNamesList != null) {
			for (String name : additionalParameterNamesList) {
				additional.append("&" + name + "="
						+ additionalParameterValuesList.get(index++));
			}
		}

		sb.append("<script type=\"text/javascript\">\n");
		sb.append("function editRow" + name + "() {\n");
		sb.append("var row = $('#" + name
				+ "_grid').jqGrid('getGridParam','selrow');\n");
		sb.append("if( row != null )\n");
		sb.append("$('#" + name + "_grid').jqGrid('editGridRow',row,\n");
		sb.append("{	url: '" + link + "?edit=true&gridname=" + name
				+ additional.toString() + "',\n");
		sb.append("editData: {\n");
		sb.append("},\n");
		sb.append("recreateForm: true,\n");
		sb.append("beforeShowForm: function(form) {\n");
		sb.append("},\n");
		sb.append("closeAfterEdit: true,\n");
		sb.append("reloadAfterSubmit:true,\n");
		sb.append("afterSubmit : function(response, postdata) {\n");
		sb.append("var result = eval('(' + response.responseText + ')');\n");
		sb.append("var errors = '';\n");
		sb.append("if (result.success == false) {\n");
		sb.append("for (var i = 0; i < result.message.length; i++) {\n");
		sb.append("errors +=  result.message[i] + ' ';\n");
		sb.append("}\n");
		sb.append("}\n");
		sb.append("else {\n");
		if (onSuccessCallback != null && !onSuccessCallback.isEmpty()) {
			sb.append(onSuccessCallback + "('edit');\n");
		}
		sb.append("$('#" + name
				+ "_dialog').text('Entry has been edited successfully');\n");
		sb.append("$('#" + name + "_dialog').dialog(\n");
		sb.append("{	title: 'Success',\n");
		sb.append("modal: true,\n");
		sb.append("buttons: {'Ok': function()  {\n");
		sb.append("$(this).dialog('close');}\n");
		sb.append("}\n");
		sb.append("});\n");
		sb.append("}\n");
		sb.append("return [result.success, errors, null];\n");
		sb.append("}\n");
		sb.append("});\n");
		sb.append("else $( '#" + name + "_dialogSelectRow' ).dialog();\n");
		sb.append("}\n");
		sb.append("</script>\n");
	}

	protected void appendDeleteRowCode(StringBuilder sb) {
		int index = 0;
		StringBuilder additional = new StringBuilder();
		if (additionalParameterNamesList != null) {
			for (String name : additionalParameterNamesList) {
				additional.append("&" + name + "="
						+ additionalParameterValuesList.get(index++));
			}
		}

		sb.append("<script type=\"text/javascript\">\n");
		sb.append("function deleteRow" + name + "() {\n");
		sb.append("var row = $('#" + name
				+ "_grid').jqGrid('getGridParam','selrow');\n");
		sb.append("if( row != null )\n");
		sb.append("$('#" + name + "_grid').jqGrid( 'delGridRow', row,\n");
		sb.append("{	url: '" + link + "?delete=true&gridname=" + name
				+ additional.toString() + "',\n");
		sb.append("recreateForm: true,\n");
		sb.append("beforeShowForm: function(form) {\n");
		sb.append("$('.delmsg').replaceWith('<span style=\"white-space: pre;\">' +\n");
		sb.append("'Delete selected record?' + '</span>');\n");
		sb.append("$('#pData').hide();\n");
		sb.append("$('#nData').hide();\n");
		sb.append("},\n");
		sb.append("reloadAfterSubmit:true,\n");
		sb.append("closeAfterDelete: true,\n");
		sb.append("afterSubmit : function(response, postdata)\n");
		sb.append("{\n");
		sb.append("var result = eval('(' + response.responseText + ')');\n");
		sb.append("var errors = '';\n");
		sb.append("if (result.success == false) {\n");
		sb.append("for (var i = 0; i < result.message.length; i++) {\n");
		sb.append("errors +=  result.message[i] + ' ';\n");
		sb.append("}\n");
		sb.append("}\n");
		sb.append("else {\n");
		if (onSuccessCallback != null && !onSuccessCallback.isEmpty()) {
			sb.append(onSuccessCallback + "('delete');\n");
		}
		sb.append("$('#" + name
				+ "_dialog').text('Entry has been deleted successfully');\n");
		sb.append("$('#" + name + "_dialog').dialog(\n");
		sb.append("{	title: 'Success',\n");
		sb.append("modal: true,\n");
		sb.append("buttons: {'Ok': function()  {\n");
		sb.append("$(this).dialog('close');}\n");
		sb.append("}\n");
		sb.append("});\n");
		sb.append("}\n");
		sb.append("var new_id = null;\n");
		sb.append("return [result.success, errors, new_id];\n");
		sb.append("}\n");
		sb.append("}\n");
		sb.append(");\n");
		sb.append("else $( '#" + name + "_dialogSelectRow' ).dialog();\n");
		sb.append("}\n");
		sb.append("</script>\n");
	}

	protected void appendHtmlCode(StringBuilder sb) {
		sb.append("<div id=\"" + name + "_jqgrid\">\n");
		sb.append("<table id=\"" + name + "_grid\"></table>\n");
		sb.append("<div id=\"" + name + "_pager\"></div>\n");
		sb.append("</div>\n");
		sb.append("<div id=\""
				+ name
				+ "_dialog\" title=\"Feature not supported\" style=\"display:none\">\n");
		sb.append("<p>That feature is not supported.</p>\n");
		sb.append("</div>\n");
		sb.append("<div id=\""
				+ name
				+ "_dialogSelectRow\" title=\"Warning\" style=\"display:none\">\n");
		sb.append("<p>Please select row</p>\n");
		sb.append("</div>\n");
	}
}