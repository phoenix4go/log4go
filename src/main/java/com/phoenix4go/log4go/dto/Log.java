package com.phoenix4go.log4go.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
@Builder
@Data
public class Log {

	private Date dataHora;
	private LogType logType;
	private String log;
	private String sistema;
	private String divisao;
	private String clazzName;
	private String document;
	
}
