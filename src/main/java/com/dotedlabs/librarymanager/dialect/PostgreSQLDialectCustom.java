package com.dotedlabs.librarymanager.dialect;

import java.sql.Types;

import org.hibernate.dialect.PostgreSQL94Dialect;

public class PostgreSQLDialectCustom extends PostgreSQL94Dialect {
	public PostgreSQLDialectCustom() {
		super();
		registerColumnType(Types.ARRAY, "int8[$1]");
		registerColumnType(Types.ARRAY, "text[]");
		registerColumnType(Types.JAVA_OBJECT, "json");
	}
}
