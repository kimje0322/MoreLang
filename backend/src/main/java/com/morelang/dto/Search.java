
package com.morelang.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Search {

	private String id;
	private String videoUrl;
	private String imgUrl;
	private String title;
	private String description;
	private List<Caption> captions;

}
