<div class="modal vacancy_feedback_modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">.
            <form method="post" action="customer-dashboard-vacancy-feedback.action">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Feedback</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <input id="vacancy_feedback_id" name="vacancyId" type="hidden">
                    </div>
                    <div class="form-group">
                        <p>Message for company: </p>
                        <textarea id="feedback_message" name="message" type="text" required class="form-control"></textarea>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-danger add-btn">Feedback</button>
                    </div>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->