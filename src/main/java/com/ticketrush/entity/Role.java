package com.ticketrush.entity;

/**
 * Role được dùng như enum trong User (@Enumerated(EnumType.STRING))
 * Không phải @Entity riêng biệt
 */
public enum Role {
    ROLE_USER,
    ROLE_ADMIN
}
