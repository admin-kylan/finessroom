package com.yj.dal.dto;

import com.yj.dal.model.FrTraningClass;
import java.util.List;

/**
 * Created by linzhq on 2018/11/28.
 */
public class FrTraningClassDTO extends FrTraningClass {
	private String image;
	private String name;

	public void setImage(String image) {
		this.image = image;
	}



	public String getImage() {
		return image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {

		this.name = name;
	}


	/**
     *

     */

}
