package com.tennisscoreboard.service.dao;

import java.util.Optional;
import java.util.UUID;

public interface Dao<T> {

    void create(T t);
}
