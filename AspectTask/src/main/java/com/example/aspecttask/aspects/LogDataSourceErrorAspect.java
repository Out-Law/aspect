package com.example.aspecttask.aspects;

import com.example.aspecttask.repositories.DataSourceErrorLogRepository;
import lombok.AllArgsConstructor;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import com.example.aspecttask.entities.DataSourceErrorLog;
import com.example.aspecttask.annotations.LogDataSourceError;

@Aspect
@Component
@AllArgsConstructor
public class LogDataSourceErrorAspect {

    private final DataSourceErrorLogRepository errorLogRepository;

    @Pointcut("@annotation(LogDataSourceError)")
    public void logDataSourceErrorPointcut() {
    }

    @AfterThrowing(pointcut = "logDataSourceErrorPointcut()", throwing = "ex")
    public void logError(Exception ex) {
        DataSourceErrorLog errorLog = new DataSourceErrorLog();
        errorLog.setStackTrace(getStackTrace(ex));
        errorLog.setMessage(ex.getMessage());
        errorLog.setMethodSignature(ex.getStackTrace()[0].toString()); // Получаем первую строку стектрейса для метода

        errorLogRepository.save(errorLog);
    }

    private String getStackTrace(Throwable ex) {
        StringBuilder result = new StringBuilder();
        for (StackTraceElement element : ex.getStackTrace()) {
            result.append(element.toString()).append("\n");
        }
        return result.toString();
    }
}
