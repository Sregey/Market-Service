<div class="modal vacancy_edit_modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">.
            <form method="post" action="company-dashboard-vacancy-add-update.action">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Update detail info</h4>
                </div>
                <div class="modal-body">
                    <input id="vacancy_edit_id" name="vacancy.id" type="hidden">
                    <div class="form-group">
                        <label>Title: </label>
                        <input id="vacancy_edit_title" type="text" required class="form-control" name="vacancy.title">
                    </div>
                    <div class="form-group">
                        <label>Description: </label>
                        <textarea id="vacancy_edit_description" type="text" required class="form-control" name="vacancy.description"></textarea>
                    </div>
                    <div class="form-group">
                        <label>Email: </label>
                        <input id="vacancy_edit_email" type="text" required class="form-control" name="vacancy.email">
                    </div>
                    <div class="form-group">
                        <label>Phone: </label>
                        <input id="vacancy_edit_phone" type="text" required class="form-control" name="vacancy.phone">
                    </div>
                    <div class="form-group">
                        <label>Skills: </label>
                        <textarea id="vacancy_edit_skills" type="text" required class="form-control" name="vacancy.skills"></textarea>
                    </div>
                    <div class="form-group">
                        <label>Salary: </label>
                        <input id="vacancy_edit_salary" type="number" required class="form-control" name="vacancy.salary">
                    </div>
                    <div class="form-group">
                        <label>Category: </label>
                        <select required class="form-control" name="vacancy.category.id">
                            <s:iterator value="categoriesList" var="category">
                                <option <s:if test="%{#id == #categoryId}">selected</s:if>
                                        value="<s:property value="id"></s:property>">
                                    <s:property value="name"></s:property>
                                </option>
                            </s:iterator>
                        </select>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary add-btn">Save changes</button>
                    </div>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

