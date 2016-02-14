package com.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.bean.Batch;
import com.dao.BatchDao;


@Component(value="validator1")
public class BatchValidator implements Validator {
@Autowired
private BatchDao bdao;
	@Override
	public boolean supports(Class<?> arg0) {
		return arg0.equals(Batch.class);
	}

	@Override
	public void validate(Object object, Errors errors) {
		Batch batch=(Batch)object;

		/* ValidationUtils.rejectIfEmptyOrWhitespace(errors, "batch_name", "name.required");
       if(((Batch) batch).getNo_of_cand()<0){
           errors.rejectValue("no_of_cand", "negative", new Object[]{"'no_of_cand'"}, "id can't be negative");
       }

       ValidationUtils.rejectIfEmptyOrWhitespace(errors, "batch_venue", "venue.required");
       ValidationUtils.rejectIfEmptyOrWhitespace(errors, "batch_type", "type.required");
      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "batch_city", "city.required");
       ValidationUtils.rejectIfEmptyOrWhitespace(errors, "no_of_cand", "no_of_cand.required");*/



		if(batch.getBatch_name() ==null || batch.getBatch_name().isEmpty()){
			errors.rejectValue("batch_name", "name.required","please enter name of the batch");
		}
		if(batch.getBatch_venue() ==null || batch.getBatch_venue().isEmpty()){
			errors.rejectValue("batch_venue", "venue.required","please enter the venue");
		}
		/* if(batch.getBatch_type()==null || batch.getBatch_type().toString()=="select"){
           errors.rejectValue("batch_type", "type.required","Please Enter Type of batch");        	
        }*/
		/* if(batch.getBatch_city()==null || batch.getBatch_city().isEmpty()){
            errors.rejectValue("batch_city", "city.required","Please Enter the City");        	
         }*/
		if(batch.getNo_of_cand() <5 ){
			errors.rejectValue("no_of_cand", "no_of_cand.required", "number of candidates can't be less than 5");
		}
		if(batch.getNo_of_cand() <0 ){
			errors.rejectValue("no_of_cand", "no_of_cand.required", "number of candidates can't be negative");
		}

		if(batch.getNo_of_cand() >41 ){
			errors.rejectValue("no_of_cand", "no_of_cand.required1", "number of candidates can't be more than 40");
		}

		//not working
		if(batch.getStart_date()==null){
			errors.rejectValue("start_date", "start_date.required","Please Enter the starting date");        	
		}

		List<Batch> batches=bdao.getAllBatch();
		for (Batch batch2 : batches) {
			if(batch2.getBatch_name().equals(batch.getBatch_name())){
				errors.rejectValue("batch_name", "name.required1","please enter another name of the batch");
				break;
			}
		}

	}
}


