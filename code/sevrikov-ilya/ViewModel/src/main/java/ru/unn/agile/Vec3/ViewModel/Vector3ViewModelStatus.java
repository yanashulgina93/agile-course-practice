package ru.unn.agile.Vec3.ViewModel;

import ru.unn.agile.Vec3.Model.Vector3;

public final class Vector3ViewModelStatus {
    public final static String OK = "OK";
    public final static String FIRST_VECTOR_WRONG_FORMAT = "Components of first vector have wrong format.";
    public final static String SECOND_VECTOR_WRONG_FORMAT = "Components of second vector have wrong format.";
    public final static String FIRST_VECTOR_SMALL_NUMBERS = "Components of first vector are small";
    public final static String SECOND_VECTOR_SMALL_NUMBERS = "Components of second vector are small";
    public final static String COPLANAR_VECTORS = "Vectors are coplanar.";
    public final static String LOGGER_CANNOT_BE_NULL = "Logger can't be null.";

    public Vector3ViewModelStatus() { }
}
