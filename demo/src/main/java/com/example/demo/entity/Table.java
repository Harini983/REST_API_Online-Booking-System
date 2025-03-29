package com.example.demo.entity;

import jakarta.persistence.UniqueConstraint;

public @interface Table {

    UniqueConstraint[] uniqueConstraints();

}
