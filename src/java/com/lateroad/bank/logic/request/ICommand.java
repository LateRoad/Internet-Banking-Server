package com.lateroad.bank.logic.request;

public interface ICommand {
    String execute(String context);
}