<div class="modal resume_edit_modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">.
            <form method="post" action="adminpanel-resume-update.action">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Update detail info</h4>
                </div>
                <div class="modal-body">
                    <input id="resume_edit_id" name="resume.id" type="hidden">
                    <input id="resume_edit_customer_id" name="resume.customer.id" type="hidden">
                    <input id="resume_edit_birthdate" name="resume.birthdate" type="hidden">
                    <div class="form-group">
                        <label>First name: </label>
                        <input id="resume_edit_firstname" type="text" required class="form-control" name="resume.firstname">
                    </div>
                    <div class="form-group">
                        <label>Last name: </label>
                        <input id="resume_edit_lastname" type="text" required class="form-control" name="resume.lastname">
                    </div>
                    <div class="form-group">
                        <label>Middle name: </label>
                        <input id="resume_edit_middlename" type="text" required class="form-control" name="resume.middlename">
                    </div>
                    <div class="form-group">
                        <label>Skills: </label>
                        <textarea id="resume_edit_skills" type="text" required class="form-control" name="resume.skills"></textarea>
                    </div>
                    <div class="form-group">
                        <label>Description: </label>
                        <textarea id="resume_edit_description" required class="form-control" name="resume.description"></textarea>
                    </div>
                    <div class="form-group">
                        <label>Address: </label>
                        <input id="resume_edit_address" type="text" required class="form-control" name="resume.address">
                    </div>
                    <div class="form-group">
                        <label>Phone: </label>
                        <input id="resume_edit_phone" type="text" required class="form-control" name="resume.phone">
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

