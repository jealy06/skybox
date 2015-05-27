package com.skybox.dbmanager;

import java.util.ArrayList;

import com.skybox.model.NFLplayerstats;

public class NFLplayerstatsDBManager extends DBManager<NFLplayerstats>{

	@Override
	public void connect() {
		// TODO Auto-generated method stub
		super.connect();
	}

	@Override
	public ArrayList<? extends Object> getList() {
		// TODO Auto-generated method stub
		return super.getList();
	}

	@Override
	public Object getItem(int id) {
		// TODO Auto-generated method stub
		return super.getItem(id);
	}

	@Override
	public int createItem(NFLplayerstats entry) {
		// TODO Auto-generated method stub
		return super.createItem(entry);
	}

	@Override
	public boolean updateItem(NFLplayerstats entry) {
		// TODO Auto-generated method stub
		return super.updateItem(entry);
	}

	@Override
	public boolean deleteItem(int id) {
		// TODO Auto-generated method stub
		return super.deleteItem(id);
	}

	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub
		super.closeConnection();
	}

}
