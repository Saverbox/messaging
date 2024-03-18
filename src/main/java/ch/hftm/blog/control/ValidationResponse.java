package ch.hftm.blog.control;

import java.util.List;

public record ValidationResponse(long id, boolean valid, List<String> reasons) {}
