package DesignPatterns.BehavioralPatterns;

abstract class ExamHall {

    ExamHall examHall;

    void setNextExamHall(ExamHall examHall) {
        this.examHall = examHall;
    }

    abstract void examHallDetails();

    abstract void getExamHall(int id);
}

class ExamHallA extends ExamHall {

    @Override
    void examHallDetails() {
        System.out.println("Exam Hall Details: Block 1 - ExamHallA ");
    }

    @Override
    void getExamHall(int id) {
        if (id < 51) {
            examHallDetails();
            return;
        }
        examHall.getExamHall(id);
    }
}

class ExamHallB extends ExamHall {

    @Override
    void examHallDetails() {
        System.out.println("Exam Hall Details: Block 1 - ExamHallB ");
    }

    @Override
    void getExamHall(int id) {
        if (id < 101) {
            examHallDetails();
            return;
        }
        examHall.getExamHall(id);
    }
}

class ExamHallC extends ExamHall {

    @Override
    void examHallDetails() {
        System.out.println("Exam Hall Details: Block 1 - ExamHallC ");
    }

    @Override
    void getExamHall(int id) {
        if (id < 151) {
            examHallDetails();
            return;
        }
        // this is the last class so we don't use next hall here
        System.out.println("Can you check your id once, it seems to be incorrect");
        //examHall.getExamHall(id);
    }
}

class ExamHallAdministrator {
    ExamHall firstHall;
    ExamHall examHallA;
    ExamHall examHallB;
    ExamHall examHallC;

    ExamHallAdministrator() {
        examHallA = new ExamHallA();
        examHallB = new ExamHallB();
        examHallC = new ExamHallC();
        examHallA.setNextExamHall(examHallB);
        examHallB.setNextExamHall(examHallC);
        firstHall=examHallA;
    }

    void getStudentExamHall(int id) {
        firstHall.getExamHall(id);
    }
}

public class ChainOfResponsibilityPattern {
    public static void main(String[] args) {
        ExamHallAdministrator teacher = new ExamHallAdministrator();
        teacher.getStudentExamHall(72);
    }
}
