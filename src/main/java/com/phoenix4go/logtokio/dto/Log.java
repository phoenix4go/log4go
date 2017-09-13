package com.phoenix4go.logtokio.dto;

import java.util.Calendar;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
@Builder
@Data
public class Log {

	private Calendar dataHora;
	private LogType logType;
	private String log;
	private String sistema;
	private String divisao;
	private String clazzName;
	private String document;
	
}
