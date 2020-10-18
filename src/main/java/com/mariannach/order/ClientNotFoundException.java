package com.mariannach.order;


class ClientNotFoundException extends RuntimeException {

    ClientNotFoundException(Long id) {
        super("Could not find client " + id);
    }
}
