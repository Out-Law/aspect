package com.example.aspecttask.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "dataSourceErrorLog")
public class DataSourceErrorLog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dataSourceErrorLogs_seq")
    @SequenceGenerator(name = "dataSourceErrorLogs_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "stack_trace", nullable = false, columnDefinition = "TEXT")
    private String stackTrace;

    @Column(name = "message")
    private String message;

    @Column(name = "method_signature", nullable = false, length = 255)
    private String methodSignature;

}
