package com.ahom.hrms.exception;import org.springframework.dao.DataIntegrityViolationException;public class CustomDataIntegrityViolationException extends DataIntegrityViolationException {    public CustomDataIntegrityViolationException(String msg) {        super(msg);    }}