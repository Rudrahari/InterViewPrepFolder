public class FilteringGreater implements Filtering {
    @Override
    public boolean filterBasedOnCondition(University university,int count) {
        return university.getStudentCount()>count;
    }
}
