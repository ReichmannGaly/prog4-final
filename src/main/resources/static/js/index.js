function redirectToEmployeePage(id) {
    window.location.href = "/employees/" + id;
}

function handleSortOptionChange() {
    const sortOptionSelect = document.querySelector('.sort-option');
    const selectedValue = sortOptionSelect.value;
    const radioAsc = document.getElementById('sortAsc');
    const radioDesc = document.getElementById('sortDesc');
    const radioAscLabel = document.querySelector('label[for="sortAsc"]');
    const radioDescLabel = document.querySelector('label[for="sortDesc"]');
    const startDateInput = document.getElementById('startDate');
    const endDateInput = document.getElementById('endDate');
    const startDateLabel = document.querySelector('label[for="startDate"]');
    const endDateLabel = document.querySelector('label[for="endDate"]');

    if (selectedValue === 'gender') {
        radioAsc.style.display = 'inline';
        radioDesc.style.display = 'inline';
        radioAscLabel.style.display = 'inline';
        radioDescLabel.style.display = 'inline';
        radioAsc.value = 'H';
        radioDesc.value = 'F';
        radioAscLabel.innerText = 'Homme';
        radioDescLabel.innerText = 'Femme';
        radioAsc.checked = true;

        startDateLabel.style.display = 'none'
        endDateLabel.style.display = 'none'
        startDateInput.style.display = 'none';
        endDateInput.style.display = 'none';
    } else if (selectedValue === 'hireDate' || selectedValue === 'resignationDate') {
        radioAsc.style.display = 'none';
        radioDesc.style.display = 'none';
        radioAscLabel.style.display = 'none';
        radioDescLabel.style.display = 'none';

        startDateLabel.style.display = 'inline';
        endDateLabel.style.display = 'inline'
        startDateInput.style.display = 'inline';
        endDateInput.style.display = 'inline';
    } else {
        radioAsc.style.display = 'inline';
        radioDesc.style.display = 'inline';
        radioAscLabel.style.display = 'inline';
        radioDescLabel.style.display = 'inline';
        radioAsc.value = 'asc';
        radioDesc.value = 'desc';
        radioAscLabel.innerText = 'Croissant';
        radioDescLabel.innerText = 'Décroissant';
        radioAsc.checked = true;

        startDateLabel.style.display = 'none'
        endDateLabel.style.display = 'none'
        startDateInput.style.display = 'none';
        endDateInput.style.display = 'none';
    }
}

handleSortOptionChange();