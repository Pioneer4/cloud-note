/* scripts/note.js */

var SUCCESS = 0;
var ERROR = 1;

$(function(){

	$(document).data('page', 0);
	loadPageNotebook();
	$('#notebook-list').on('click', ".more", loadPageNotebook);

	//网页加载后，立即读取笔记本列表
//	loadNotebooks();

	$('#notebook-list').on('click','.notebook', loadNotes);
	$('#note-list').on('click', '#add_note', showAddNoteDialog);
	$('#can').on('click', '.create-note', addNote);
	$('#can').on('click', '.close, .cancel', closeDialog);
	$('#note-list').on('click', '.note', loadNote);
	$('#save_note').on('click',updateNote);
	//笔记子菜单触发
	$('#note-list').on('click', '.btn-note-menu', showNoteMenu);
	//移动笔记功能
	$('#note-list').on('click', '.btn_move', showMoveNoteDialog);
	$('#can').on('click', '.move-note', moveNote);
	//删除笔记功能
	$('#note-list').on('click', '.btn_delete', showDeleteNoteDialog);
	$('#can').on('click', '.delete-note', deleteNote);

	$('#trash-bin').on('click', '.disable', loadNote);
	//监听回收站按钮点击事件
	$('#trash-button').click(showTrashBin);
	//恢复回收站笔记
	$('#trash-bin').on('click', '.btn_replay', showReplayDialog);
	$('#can').on('click', '.replay-note', replayNote);
	//彻底删除回收站笔记
	$('#trash-bin').on('click', '.btn_delete', showRemoveDialog);
	$('#can').on('click', '.delete-rollback', deleteRollback);

	startHeartBeat();
});

function startHeartBeat() {
	var url = 'user/heart.do';
	setTimeout(function(){
		$.getJSON(url, function(result){
//			console.log(result.message);
		});
	}, 5000);
}

function loadPageNotebook() {
	var page = $(document).data('page');
	var userId = getCookie('userId');
	var url = 'notebook/page.do';
	var data = {userId:userId, page:page};
	$.getJSON(url, data, function(result){
		if (result.state == SUCCESS) {
			var notebooks = result.data;
			showPageNotebooks(notebooks, page);
			$(document).data('page', page+1);
		} else {
			alert(result.message);
		}
	});
}

function showPageNotebooks(notebooks, page) {
	var ul = $('#notebook-list ul');
	if (page ==0 ) {
		ul.empty();
	} else {
		ul.find('.more').remove();
	}
	for (var i=0; i<notebooks.length; i++) {
		var notebook = notebooks[i];
		var li = notebookTemplate.replace('[name]', notebook.name);
		li = $(li);
		li.data('notebookId', notebook.id);
		ul.append(li);
	}
	if (notebooks.length != 0) {
		ul.append(moreTemplate);
	}
}

var moreTemplate =
	'<li class="online more">' +
	'<a><i class="fa fa-plus" title="online" rel="tooltip-bottom"></i>[加载更多...]</a>' +
	'<li>';

function loadNotebooks() {
	//利用ajax从服务器获取（get）数据
	var url = 'notebook/list.do';
	var data = {userId:getCookie('userId')};
	$.getJSON(url, data, function(result){
		if (result.state == SUCCESS) {
			var notebooks = result.data;
			showNotebooks(notebooks);
		} else {
			alert(result.message);
		}
	});

}

/* 在notebook-list区域中显示笔记本列表 */
function showNotebooks(notebooks) {
	//找到显示笔记本列表的区域
	//遍历notebooks数组，将每个对象创建一个li
	//语素，添加到ul元素中
	var ul = $('#notebook-list ul')
	ul.empty();
	for (var i=0; i<notebooks.length; i++) {
		var notebook = notebooks[i];
		var li = notebookTemplate.replace('[name]', notebook.name);
		li = $(li);

		//将notebook.id绑定到li
		li.data('notebookId', notebook.id);
		ul.append(li);
	}
}

var notebookTemplate =
	'<li class="online notebook">' +
	'<a><i class="fa fa-book" title="online" rel="tooltip-bottom"></i>[name]</a>' +
	'<li>';

/** 笔记本项目点击事件处理方法，加载该笔记本的全部笔记 */
function loadNotes() {
	//关闭回收站 打开笔记本列表
	$('#trash-bin').hide();
	$('#note-list').show();

	var li = $(this);

	$(document).data('notebookId', li.data('notebookId'));

	//在被点击的笔记本li增加选定效果
	li.parent().find('a').removeClass('checked');
	li.find('a').addClass('checked');

	var url = 'note/list.do';
	var data = {notebookId:li.data('notebookId')};
	$.getJSON(url, data, function(result){
		if (result.state == SUCCESS) {
			var notes = result.data;
			showNotes(notes);
		} else {
			alert(result.message);
		}
	});
}

function showNotes(notes) {
	var ul = $('#note-list ul');
	ul.empty();
	for (var i=0; i<notes.length; i++) {
		var note = notes[i];
		var li = noteTemplate.replace('[title]', note.title);
		li = $(li);
		ul.append(li);

		// 绑定noteId
		li.data("noteId", note.id);
	}

}

var noteTemplate =
	'<li class="online note">' +
	'<a  class="">' +
		'<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> [title]<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down btn-note-menu"><i class="fa fa-chevron-down"></i></button>' +
	'</a>' +
	'<div class="note_menu" tabindex="-1">' +
		'<dl>' +
			'<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>' +
			'<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>' +
			'<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>' +
		'</dl>' +
	'</div>' +
	'</li>'

function showAddNoteDialog() {
	var notebookId = $(document).data('notebookId');
	if (notebookId != null) {
		$('#can').load('alert/alert_note.html',function(){
			$('.opacity_bg').show();
			$('#input_note').focus();
		});
		return;
	}
	alert("选择要加入的笔记本");
}

function addNote() {
	var url = 'note/add.do';
	var userId = getCookie('userId');
	var notebookId = $(document).data('notebookId');
	var title = $('#can #input_note').val();
	var data = {userId:userId, notebookId:notebookId, title:title};
	// console.log(data);
	$.post(url, data, function(result){
		if (result.state == SUCCESS) {
			var note = result.data;
			showNote(note);
			var ul = $('#note-list ul');
			var li = noteTemplate.replace('[title]', note.title);
			li = $(li);
			li.data("noteId", note.id);
			ul.find('a').removeClass('checked');
			li.find('a').addClass('checked');
			ul.prepend(li);
			closeDialog();
		} else {
			alert(result.message);
		}
	});
}

	function closeDialog() {
	$('.opacity_bg').hide();
	$('#can').empty();
}

function loadNote() {
	var li = $(this);
	var id = li.data('noteId');

	li.parent().find('a').removeClass('checked');
    li.find('a').addClass('checked');

	var url = 'note/load.do';
    var data= {noteId: id };

	$.getJSON(url, data, function(result){
	   // console.log(result);
	   if(result.state == SUCCESS){
		   var note = result.data;
		   showNote(note);
	   }else{
		   alert(result.message);
	   }
   });
}

function showNote(note){
    //显示笔记标题
    $('#input_note_title').val(note.title);
    //显示笔记内容
    um.setContent(note.body);
	//绑定note
	$(document).data('note', note);
}

function updateNote() {
	//alert('updateNote');
	var modifiedFlag = false;
	var url = 'note/update.do';
	var note = $(document).data('note');
	var data = {noteId:note.id};
	var title = $('#input_note_title').val();
	if (title && title!=note.title) {
		data.title = title;
		modifiedFlag = true;
	}
	var body = um.getContent();
	if (body != note.body) {
		data.body = body;
		modifiedFlag = true;
	}
	/** 如果信息发生修改，则移交修改内容 */
	if (modifiedFlag) {
		$.post(url, data, function(result){
			if (result.state == SUCCESS) {
				note.title = title;
				note.body = body;
				var l = $('#note-list .checked').parent();
				$('#note-list .checked').remove();
				var li = noteTemplate.replace( '[title]', title);
				var a = $(li).find('a');
				a.addClass('checked');
				l.prepend(a);
			} else {
				alert(result.message);
			}
		});
	}
}

function showNoteMenu() {
	$(this).parent('.checked').next().toggle();
	$(document).click(hideNoteMenu);

	return false; //阻止点击事件的继续传播
}


function hideNoteMenu() {
	$('.note_menu').hide();
}

function showMoveNoteDialog() {
	var id = $(document).data('note').id;
	if (id) {
		$('#can').load('alert/alert_move.html', loadNotebookOptions);
		$('.opacity_bg').show();
		return
	}
	alert('选择要移动的笔记');
}

function loadNotebookOptions() {
	var url = 'notebook/list.do'
	var data = {userId:getCookie('userId')}

	$.getJSON(url, data, function(result){
		if (result.state == SUCCESS)
		{
			var notebooks = result.data;
			//添加笔记本列表的下拉菜单
			$('#moveSelect').empty();
			var id = $(document).data('notebookId');
			for (var i=0; i<notebooks.length; i++) {
				var notebook = notebooks[i];
				var opt = $('<option></option>').val(notebook.id).html(notebook.name);
				if (notebook.id  == id) {
					opt.attr('selected', 'selected');
				}
				$('#moveSelect').append(opt);
			}
		} else {
			alert(result.message);
		}
	});
}

function moveNote() {
	var url = 'note/move.do';
	var id = $(document).data('note').id;
	var notebookId = $('#moveSelect').val();
	if (notebookId == $(document).data('notebookId')) {
		return;
	}
	var data = {noteId:id, notebookId:notebookId};

	$.post(url, data, function(result){
		if (result.state == SUCCESS) {
			li = $('#note-list .contacts-list .checked').parent();
			lis = li.siblings();
			if (lis.size() > 0) {
				li.eq(0).click();
			} else {
				$('#input_note_title').val('');
				um.setContent('');
			}
			li.remove();
			closeDialog();
		} else {
			alert(result.message);
		}
	});
}

function showDeleteNoteDialog() {
	var id = $(document).data('note').id;
	if (id) {
		$('#can').load('alert/alert_delete_note.html', loadNotebookOptions);
        $('.opacity_bg').show();
        return;
	}
	alert('选择要删除的笔记');
}

function deleteNote() {
	var url = 'note/delete.do';
	var id = $(document).data('note').id;
	var data = {noteId:id};
	$.post(url, data, function(result){
		if (result.state == SUCCESS) {
			li = $('#note-list .contacts-list .checked').parent();
			li.remove();
			$('#input_note_title').val('');
			um.setContent('');
			closeDialog();
		} else {
			alert(result.message);
		}
	});
}

function showTrashBin()  {
	$('#trash-bin').show();
	$('#note-list').hide();
	loadTrashBein();
}

function loadTrashBein() {
	var url = 'note/trash.do';
	var data = {userId:getCookie('userId')};
	$.getJSON(url, data, function(result){
		if (result.state == SUCCESS) {
			showNotesInTrashBin(result.data);
		} else {
			alert(result.message);
		}
	});
}

function showNotesInTrashBin(notes) {
	var ul = $('#trash-bin ul');
	ul.empty();
	for (var i=0; i<notes.length; i++) {
		var note = notes[i];
		var li = deleteNoteTemplate.replace('[title]', note.title);
		li = $(li);
		ul.append(li);

		// 绑定noteId
		li.data('noteId', note.id);
	}
}

var deleteNoteTemplate =
	'<li class="disable">' +
		'<a><i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>' +
		'[title]' +
		'<button type="button" class="btn btn-default btn-xs btn_position btn_delete">' +
		'<i class="fa fa-times"></i>' +
		'</button><button type="button" class="btn btn-default btn-xs btn_position_2 btn_replay">' +
		'<i class="fa fa-reply"></i></button></a>' +
	'</li>';

function showReplayDialog() {
	var li = $(this).parent().parent();
	var id = li.data('noteId');

	if (id) {
		$('#can').load('alert/alert_replay.html', loadReplayOptions);
        $('.opacity_bg').show();
        return;
	}
	alert('选择要恢复的笔记');
}

function loadReplayOptions() {
	var url = 'notebook/list.do'
	var data = {userId:getCookie('userId')};
	$.getJSON(url, data, function(result){
		if (result.state == SUCCESS) {
			var notebooks = result.data;
			$('#replaySelect').empty();
			var id = $(document).data('notebookId');
			for (var i=0; i<notebooks.length; i++) {
				var notebook = notebooks[i];
				var opt = $('<option></option>').val(notebook.id).html(notebook.name);
				if (notebook.id  == id) {
					opt.attr('selected', 'selected');
				}
				$('#replaySelect').append(opt);
			}
		} else {
			alert(result.message);
		}
	});
}

function replayNote() {
	var url = 'note/replay.do';
	var id = $(document).data('note').id;
	var notebookId = $('#replaySelect').val();
	var data = {noteId:id, notebookId:notebookId};

	$.post(url, data, function(result){
		if (result.state == SUCCESS) {
			closeDialog();
			li = $('#trash-bin .contacts-list .checked').parent();
			$('#input_note_title').val('');
			um.setContent('');
            li.slideUp(200, function(){$(this).remove()});
		} else {
			alert(result.message);
		}
	});
}

function showRemoveDialog() {
	var id = $(document).data('note').id;
	if (id) {
		$('#can').load('alert/alert_delete_rollback.html', loadNotebookOptions);
        $('.opacity_bg').show();
        return;
	}
	alert('选择要彻底删除的笔记');
}


function deleteRollback() {
	var url = 'note/remove.do';
	var id = $(document).data('note').id;
	var data = {noteId:id};
	$.post(url, data, function(result){
		if (result.state == SUCCESS) {
			closeDialog();
			li = $('#trash-bin .contacts-list .checked').parent();
			$('#input_note_title').val('');
			um.setContent('');
            li.slideUp(200, function(){$(this).remove()});
		} else {
			alert(result.message);
		}
	});
}
