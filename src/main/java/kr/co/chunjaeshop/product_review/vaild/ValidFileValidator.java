package kr.co.chunjaeshop.product_review.vaild;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidFileValidator  implements ConstraintValidator<ValidFile, MultipartFile> {

  @Override
  public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
    return file != null && !file.isEmpty();
  }
}