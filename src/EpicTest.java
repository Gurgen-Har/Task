import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {
    Managers managers = new Managers();
    TaskManager testManager;
    Epic epic_1;
    Subtask subtask_1;
    Subtask subtask_2;

    @BeforeEach
    public void beforeEach(){
        testManager = managers.getDefault();

        epic_1 = new Epic("Epic1","dscr",testManager.getId(),Status.NEW);
        testManager.createEpic(epic_1);

        subtask_1 = new Subtask("Subtask_1","dscr",testManager.getId(),epic_1.getEpicId(),Status.NEW);
        subtask_2 = new Subtask("Subtask_w","dscr",testManager.getId(),epic_1.getEpicId(),Status.NEW);


    }
    @Test
    public void emptySubtaskForEpic(){
        assertEquals(Status.NEW, epic_1.getStatus(), "Статус без подзадач должен быть NEW");
    }
    @Test
    public void  AllSubtaskNewStatus(){
        testManager.createSubtask(subtask_1, epic_1.getEpicId());
        testManager.createSubtask(subtask_2, epic_1.getEpicId());

        assertEquals(Status.NEW, epic_1.getStatus(),"Статус должен быть NEW");

    }
    @Test
    public void  AllSubtaskDoneStatus(){
        testManager.createSubtask(subtask_1, epic_1.getEpicId());
        testManager.createSubtask(subtask_2, epic_1.getEpicId());

        testManager.updateSubtask(subtask_1.id, Status.DONE);
        testManager.updateSubtask(subtask_2.id, Status.DONE);

        assertEquals(Status.DONE, epic_1.getStatus(),"Статус должен быть DONE");
    }

    @Test
    public void SubtaskWithNewAndDoneStatus(){
        testManager.createSubtask(subtask_1, epic_1.getEpicId());
        testManager.createSubtask(subtask_2, epic_1.getEpicId());

        testManager.updateSubtask(subtask_1.id, Status.DONE);


        assertEquals(Status.IN_PROGRESS, epic_1.getStatus(),"Статус должен быть InProgress");
    }

    @Test
    public void AllSubtaskInProgressStatus(){
        testManager.createSubtask(subtask_1, epic_1.getEpicId());
        testManager.createSubtask(subtask_2, epic_1.getEpicId());

        testManager.updateSubtask(subtask_1.id, Status.IN_PROGRESS);
        testManager.updateSubtask(subtask_2.id, Status.IN_PROGRESS);

        assertEquals(Status.IN_PROGRESS, epic_1.getStatus(),"Статус должен быть InProgress");
    }

}