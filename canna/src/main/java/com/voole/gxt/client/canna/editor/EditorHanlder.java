package com.voole.gxt.client.canna.editor;

public interface EditorHanlder {
	interface AddHanlder<T> {
		void postSave(T t);
	}

	interface UpdateHanlder<T> {
		void postUpdate(T t);
	}

}
