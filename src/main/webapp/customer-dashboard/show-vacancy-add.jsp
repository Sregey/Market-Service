<div class="modal vacancy_add_modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">.
            <form method="post" action="show-items-add.action">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Feedback for vacancy</h4>
                </div>
                <div class="modal-body">
                    <input id="vacancy_add_id" name="vacancyId" type="hidden">
                    <div class="form-group">
                        <label>Title: </label>
                        <input id="vacancy_add_title" type="text" required class="form-control" name="nameItem" disabled>
                    </div>
                    <div class="form-group">
                        <label>Category: </label>
                        <input id="vacancy_add_category_name" type="text" required class="form-control" name="categoryItem" disabled>
                    </div>
                    <div class="form-group">
                        <label>Description: </label>
                        <textarea id="vacancy_add_description" type="text" required class="form-control" name="description" disabled></textarea>
                    </div>
                    <div class="form-group">
                        <label>Email: </label>
                        <input id="vacancy_add_email" type="text" required class="form-control" name="email" disabled>
                    </div>
                    <div class="form-group">
                        <label>Phone: </label>
                        <input id="vacancy_add_phone" type="text" required class="form-control" name="phone" disabled>
                    </div>
                    <div class="form-group">
                        <label>Skills: </label>
                        <textarea id="vacancy_add_skills" type="text" required class="form-control" name="skills" disabled></textarea>
                    </div>
                    <div class="form-group">
                        <label>Company name: </label>
                        <input id="vacancy_add_company_name" type="text" required class="form-control" name="companyName" disabled>
                    </div>
                    <div class="form-group">
                        <label>Salary: </label>
                        <input id="vacancy_add_salary" type="number" required class="form-control" name="salary" disabled>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary add-btn">Order</button>
                    </div>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

