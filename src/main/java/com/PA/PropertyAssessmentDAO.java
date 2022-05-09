package com.PA;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface PropertyAssessmentDAO {
    public PropertyAssessment getByAccountNumber(int accountNumber);
    public List<PropertyAssessment> getByNeighbourhood(String neighbourhood);
    public List<PropertyAssessment> getByAssessmentClass(String assessmentClass);
    public List<PropertyAssessment> getAll();
    public List<PropertyAssessment> getByAddress(String address);
    public List<PropertyAssessment> getMinAbove(int min) throws UnsupportedEncodingException;
    public List<PropertyAssessment> getMaxBelow(int max) throws UnsupportedEncodingException;
    public List<PropertyAssessment> getBetween(int min, int max) throws UnsupportedEncodingException;
}
