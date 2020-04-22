# Title     : CPU cooling model abstracted to use from Java
# Objective : Polyglot execution of R code in Quarkus
# Created by: dmi3y
# Created on: 4/22/20
cpu_grads <-          c(1.0,20.0, 30.0, 40.0,35.0, 37.5, 60.0, 68, 100, 77, 85, 99)
# We enable cooler around above 60 grad manually
cpu_cooler_enable <-  c(0,0,0,0,0,0,1,1,1,1,1,1)
y<-cpu_cooler_enable; x<-cpu_grads;
cpu_model <- glm(y~x, family = 'binomial')
new_data <- data.frame(x=c(40))
predict(cpu_model, new_data, type="response")



